package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.domain.game.organization.CreditRequest
import ru.novemis.rpgapp.domain.game.organization.CreditRequestStatus
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.organization.CreditOfferRepository
import ru.novemis.rpgapp.repository.game.organization.CreditRequestRepository
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import ru.novemis.rpgapp.service.CreditService
import ru.novemis.rpgapp.service.NotificationService
import javax.transaction.Transactional

@RestController
@RequestMapping("/credit")
open class CreditProceduresController(
        private val creditRequestRepository: CreditRequestRepository,
        private val organizationRepository: OrganizationRepository,
        private val gameCharacterRepository: GameCharacterRepository,
        private val creditOfferRepository: CreditOfferRepository,
        private val creditService: CreditService,
        private val notificationService: NotificationService
) {

    data class CreditRequestRq(
            val creditOfferId: String = "",
            val amount: Int = 0,
            val duration: Int = 0,
            val purpose: String = "",
            val organizationId: String = "",
            val requesterId: String = ""
    )

    @PostMapping("/submitCreditRequest.do")
    @Transactional
    open fun submitCreditRequest(@RequestBody rq: CreditRequestRq) {
        val organization = organizationRepository.findById(rq.organizationId).get()
        val creditOffer = creditOfferRepository.findById(rq.creditOfferId).get()
        creditRequestRepository.save(
                CreditRequest(
                        creditOffer = creditOffer,
                        duration = rq.duration,
                        amount = rq.amount,
                        currency = creditOffer.currency,
                        purpose = rq.purpose,
                        organization = organization,
                        requester = gameCharacterRepository.findById(rq.requesterId).get(),
                        status = CreditRequestStatus.PENDING
                )
        )
    }

    data class ApproveCreditRequestRq(val creditRequestId: String = "")

    @PostMapping("/approveCreditRequest.do")
    @Transactional
    open fun approveCreditRequest(@RequestBody rq: ApproveCreditRequestRq) {
        creditService.changeRequestStatus(rq.creditRequestId, CreditRequestStatus.APPROVED)
        creditService.generateCredit(rq.creditRequestId)
        creditService.transferCreditMoney(rq.creditRequestId)
        notificationService.onCreditAprooved(rq.creditRequestId)
    }

    data class RejectCreditRequestRq(val creditRequestId: String = "")

    @PostMapping("/rejectCreditRequest.do")
    @Transactional
    open fun rejectCreditRequest(@RequestBody rq: RejectCreditRequestRq) {
        creditService.changeRequestStatus(rq.creditRequestId, CreditRequestStatus.REJECTED)
        notificationService.onCreditRejected(rq.creditRequestId)
    }

    data class CreditPaymentRq(
            val creditId: String = "",
            val amount: Int = 0
    )

    @PostMapping("/creditPayment.do")
    @Transactional
    open fun creditPayment(@RequestBody rq: CreditPaymentRq) {
        creditService.makeCreditPayment(rq.creditId, rq.amount)
    }

    @PostMapping("/forcePayments.do")
    @Transactional
    open fun forceCreditPayments() {
        creditService.makeForcedPayments()
    }

}