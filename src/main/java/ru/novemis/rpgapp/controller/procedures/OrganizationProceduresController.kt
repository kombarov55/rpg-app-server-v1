package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.domain.game.organization.CreditRequest
import ru.novemis.rpgapp.domain.game.organization.CreditRequestStatus
import ru.novemis.rpgapp.repository.game.CurrencyRepository
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.organization.CreditOfferRepository
import ru.novemis.rpgapp.repository.game.organization.CreditRequestRepository
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import ru.novemis.rpgapp.repository.game.shop.ItemRepository
import ru.novemis.rpgapp.service.ItemService
import javax.transaction.Transactional

@RestController
@RequestMapping("/organization")
open class OrganizationProceduresController(
        private val itemService: ItemService,
        private val itemRepository: ItemRepository,
        private val creditRequestRepository: CreditRequestRepository,
        private val currencyRepository: CurrencyRepository,
        private val organizationRepository: OrganizationRepository,
        private val gameCharacterRepository: GameCharacterRepository,
        private val creditOfferRepository: CreditOfferRepository
) {

    data class DisposeItemRq(
            val organizationId: String = "",
            val itemId: String = ""
    )

    @PostMapping("/disposeItem.do")
    @Transactional
    open fun disposeItem(@RequestBody rq: DisposeItemRq) {
        val item = itemRepository.findById(rq.itemId).get()
        itemService.removeItemFromOrganization(rq.organizationId, item)
    }

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
        val currency = creditOfferRepository.findById(rq.creditOfferId).get().currency
        creditRequestRepository.save(
                CreditRequest(
                        duration = rq.duration,
                        amount = rq.amount,
                        currency = currency,
                        purpose = rq.purpose,
                        organization = organization,
                        requester = gameCharacterRepository.findById(rq.requesterId).get(),
                        status = CreditRequestStatus.PENDING
                )
        )
    }

}