package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.repository.game.shop.ItemRepository
import ru.novemis.rpgapp.service.ItemService
import javax.transaction.Transactional

@RestController
@RequestMapping("/organization")
open class OrganizationProceduresController(
        private val itemService: ItemService,
        private val itemRepository: ItemRepository
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

}