package ru.novemis.rpgapp.service

import org.springframework.stereotype.Service
import ru.novemis.rpgapp.domain.game.organization.Credit
import ru.novemis.rpgapp.domain.game.organization.CreditRequestStatus
import ru.novemis.rpgapp.repository.game.organization.CreditRepository
import ru.novemis.rpgapp.repository.game.organization.CreditRequestRepository
import java.util.*

@Service
open class CreditService(
        private val creditRequestRepository: CreditRequestRepository,
        private val creditRepository: CreditRepository,
        private val balanceService: BalanceService
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
                durationInDays = creditRequest.duration,
                openingDate = Date(),
                lastPaymentDate = null,
                minimalPayment = (creditRequest.amount.toDouble() / creditRequest.duration.toDouble()).toInt(),
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
            throw RuntimeException("Что то пошло не так:)", e)
        }
    }

    open fun makeCreditPayment(creditId: String, amount: Int) {
        val credit = creditRepository.findById(creditId).get()

        val gameId = credit.owner!!.game!!.id
        val characterBalanceId = credit.owner.balance!!.id
        val organiationBalanceId = credit.organization!!.balance!!.id
        val currencyName = credit.currency!!.name

        balanceService.transfer(gameId, characterBalanceId, organiationBalanceId, currencyName, amount)

        credit.lastPaymentDate = Date()
        credit.payedAmount += amount
    }

}