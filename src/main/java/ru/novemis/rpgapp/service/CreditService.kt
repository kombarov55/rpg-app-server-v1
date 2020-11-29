package ru.novemis.rpgapp.service

import org.springframework.stereotype.Service
import ru.novemis.rpgapp.domain.game.organization.Credit
import ru.novemis.rpgapp.domain.game.organization.CreditRequestStatus
import ru.novemis.rpgapp.repository.game.organization.CreditRepository
import ru.novemis.rpgapp.repository.game.organization.CreditRequestRepository
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Service
open class CreditService(
        private val creditRequestRepository: CreditRequestRepository,
        private val creditRepository: CreditRepository,
        private val balanceService: BalanceService,
        private val notificationService: NotificationService
) {

    open fun changeRequestStatus(creditRequestId: String, newStatus: CreditRequestStatus) {
        val creditRequest = creditRequestRepository.findById(creditRequestId).get()
        creditRequest.status = newStatus
        creditRequest.statusChangeDate = Date()
        creditRequestRepository.save(creditRequest)
    }

    open fun generateCredit(creditRequestId: String) {
        val creditRequest = creditRequestRepository.findById(creditRequestId).get()

        creditRepository.save(Credit(
                currency = creditRequest.currency,
                amount = creditRequest.amount,
                rate = creditRequest.creditOffer!!.rate,
                debtAmount = (
                        creditRequest.amount.toDouble() *
                                (1.0 + (creditRequest.creditOffer.rate / 100.0))
                        ).toInt(),
                durationInDays = creditRequest.duration,
                openingDate = Date(),
                organization = creditRequest.organization,
                owner = creditRequest.requester
        ))
    }

    open fun transferCreditMoney(creditRequestId: String) {
        val creditRequest = creditRequestRepository.findById(creditRequestId).get()

        val gameId = creditRequest.organization!!.game!!.id
        val organizationBalanceId = creditRequest.organization.balance!!.id
        val characterBalanceId = creditRequest.requester!!.balance!!.id
        val currencyName = creditRequest.currency!!.name
        val amount = creditRequest.amount

        try {
            balanceService.transfer(gameId, organizationBalanceId, characterBalanceId, currencyName, amount)
        } catch (e: RuntimeException) {
            throw RuntimeException("У организации недостаточно средств", e)
        }
    }

    open fun makeCreditPayment(creditId: String, amount: Int) {
        val credit = creditRepository.findById(creditId).get()

        if (credit.debtAmount < amount) {
            throw RuntimeException("Сумма слишком велика")
        }

        transferCreditPayment(credit, amount)
        credit.lastPaymentDate = Date()
        credit.payedAmount += amount
        credit.debtAmount -= amount

        if (credit.debtAmount == 0) {
            credit.isPaid = true
            credit.isOverdue = false
            notificationService.onCreditPaid(credit)
        }

        creditRepository.save(credit)
    }

    open fun makeForcedPayments() {
        findAllOverdueCredits().forEach { credit ->
            try {
                makeCreditPayment(credit.id, credit.debtAmount)
            } catch (e: Exception) {
                credit.isOverdue = true
                notificationService.onCreditOverdue(credit)
            }

            creditRepository.save(credit)
        }
    }

    private fun transferCreditPayment(credit: Credit, amount: Int) {
        val gameId = credit.owner!!.game!!.id
        val characterBalanceId = credit.owner.balance!!.id
        val organiationBalanceId = credit.organization!!.balance!!.id
        val currencyName = credit.currency!!.name

        balanceService.transfer(gameId, characterBalanceId, organiationBalanceId, currencyName, amount)
    }

    private fun findAllOverdueCredits(): List<Credit> {
        return creditRepository.findAll().filter { credit ->
            val paymentDate = Instant.ofEpochMilli(credit.openingDate.time)
                    .plus(credit.durationInDays.toLong(), ChronoUnit.DAYS)

            !credit.isPaid && paymentDate.isBefore(Instant.now())
        }
    }
}