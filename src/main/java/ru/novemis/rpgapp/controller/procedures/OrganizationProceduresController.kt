package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import ru.novemis.rpgapp.repository.game.shop.ItemRepository
import ru.novemis.rpgapp.service.ItemService
import javax.transaction.Transactional

@RestController
@RequestMapping("/organization")
open class OrganizationProceduresController(
        private val itemService: ItemService,
        private val itemRepository: ItemRepository,
        private val organizationRepository: OrganizationRepository
) {

    data class DisposeItemRq(
            val organizationId: String = "",
            val itemId: String = ""
    )

    @PostMapping("/disposeItem.do")
    @Transactional
    open fun disposeItem(@RequestBody rq: DisposeItemRq) {
        organizationRepository.findById(rq.organizationId).get()
                .removeItem(rq.itemId)
                .also { organizationRepository.save(it) }
    }

    data class EquipItemRq(
            val organizationId: String = "",
            val itemId: String = ""
    )

    @PostMapping("/equipItem.do")
    @Transactional
    open fun equipItem(@RequestBody rq: EquipItemRq) {
        organizationRepository.findById(rq.organizationId).get()
                .equipItem(rq.itemId)
                .also { organizationRepository.save(it) }
    }

    data class UnequipItemRq(
            val organizationId: String = "",
            val itemId: String = ""
    )

    @PostMapping("/unequipItem.do")
    @Transactional
    open fun unequipItem(@RequestBody rq: UnequipItemRq) {
        organizationRepository.findById(rq.organizationId).get()
                .unequipItem(rq.itemId)
                .also { organizationRepository.save(it) }
    }

}