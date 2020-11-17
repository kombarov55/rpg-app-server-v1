package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.service.NotificationService
import ru.novemis.rpgapp.service.ShopService
import javax.transaction.Transactional

@RestController
@RequestMapping("/shop")
open class ShopProceduresController(
        private val shopService: ShopService,
        private val notificationService: NotificationService
) {

    data class PurchaseRq(
            val shopId: String = "",
            val buyerBalanceId: String = "",
            val buyerCharacterId: String = "",
            val gameId: String = "",
            val itemForSaleId: String = ""
    )

    @PostMapping("/purchase.do")
    @Transactional
    open fun purchase(@RequestBody rq: PurchaseRq) {
        notificationService.sendItemBoughtNotification(rq.itemForSaleId)
        shopService.purchase(rq.shopId, rq.buyerBalanceId, rq.buyerCharacterId, rq.gameId, rq.itemForSaleId)
    }

    data class SetItemForSaleRq(
            val itemId: String = "",
            val shopId: String = "",
            val publisherId: String = "",
            val price: List<PriceForm> = emptyList()
    )

    @PostMapping("/setItemForSale.do")
    open fun setItemForSale(@RequestBody rq: SetItemForSaleRq) {
        shopService.setItemForSale(rq.itemId, rq.shopId, rq.publisherId, rq.price)
    }
}