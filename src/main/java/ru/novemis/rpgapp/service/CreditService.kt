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
        private val creditRepository: CreditRepository
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
                organization = creditRequest.organization,
                owner = creditRequest.requester
        ))
    }

}