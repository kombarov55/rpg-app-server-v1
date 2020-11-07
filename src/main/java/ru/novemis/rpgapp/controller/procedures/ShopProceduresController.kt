package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.service.BalanceService
import ru.novemis.rpgapp.service.ShopService
import javax.transaction.Transactional

@RestController
@RequestMapping("/shop")
open class ShopProceduresController(
        private val balanceService: BalanceService,
        private val service: ShopService
) {

    data class PurchaseRq(
            val buyerBalanceId: String = "",
            val price: List<PriceForm> = mutableListOf(),
            val buyerCharacterId: String = "",
            val gameId: String = "",
            val merchandiseId: String = ""
    )

    data class PurchaseRs(
            val success: Boolean = true,
            val msg: String? = null
    )

    @PostMapping("/purchaseFromGameShop.do")
    @Transactional
    open fun purchase(@RequestBody rq: PurchaseRq) {
        rq.price.forEach { amount -> balanceService.subtract(rq.buyerBalanceId, amount.name, amount.amount) }
        service.transferItemFromGame(rq.gameId, rq.buyerCharacterId, rq.merchandiseId)
    }

}