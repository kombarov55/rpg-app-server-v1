package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.service.GameService

@RestController
@RequestMapping("/game")
class GameProceduresController(
        private val gameService: GameService
) {

    data class SetItemForSaleRq(
            val itemTemplateId: String = "",
            val price: List<PriceForm> = emptyList(),
            val gameId: String = ""
    )

    @PostMapping("/setItemForSale.do")
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

}