package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.domain.game.common.TransferDestinationType
import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.service.GameService
import ru.novemis.rpgapp.service.ItemService
import ru.novemis.rpgapp.service.NotificationService
import javax.transaction.Transactional

@RestController
@RequestMapping("/game")
class GameProceduresController(
        private val gameService: GameService,
        private val itemService: ItemService,
        private val notificationService: NotificationService
) {

    data class SetItemForSaleRq(
            val itemTemplateId: String = "",
            val price: List<PriceForm> = emptyList(),
            val gameId: String = ""
    )

    @PostMapping("/addItemForSale.do")
    fun setItemForSale(@RequestBody rq: SetItemForSaleRq) {
        gameService.addItemForSale(rq.gameId, rq.itemTemplateId, rq.price)
    }

    data class PurchaseItemRq(
            val itemForSaleId: String = "",
            val gameId: String = "",
            val characterId: String = "",
            val balanceId: String = ""
    )

    @PostMapping("/purchaseItem.do")
    fun purchaseItem(@RequestBody rq: PurchaseItemRq) {
        gameService.purchaseItem(rq.itemForSaleId, rq.gameId, rq.characterId, rq.balanceId)
    }

    data class TransferItemRq(
            val itemId: String = "",
            val fromType: TransferDestinationType = TransferDestinationType.CHARACTER,
            val fromId: String = "",
            val toType: TransferDestinationType = TransferDestinationType.CHARACTER,
            val toId: String = ""
    )

    @PostMapping("/transferItem.do")
    fun transferItem(@RequestBody rq: TransferItemRq) {
        itemService.transferItem(rq.itemId, rq.fromType, rq.fromId, rq.toType, rq.toId)
        notificationService.onItemTransfered(rq.itemId, rq.fromId, rq.fromType, rq.toId, rq.toType)
    }

}