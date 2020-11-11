package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.service.GameCharacterService
import ru.novemis.rpgapp.service.GameService
import javax.transaction.Transactional

@RestController
@RequestMapping("/game")
open class GameProceduresController(
        private val gameService: GameService,
        private val gameCharacterService: GameCharacterService
) {

    data class SetItemForSaleRq(
            val merchandiseId: String = "",
            val price: List<PriceForm> = emptyList(),
            val gameId: String = ""
    )

    @PostMapping("/setItemForSale.do")
    @Transactional
    open fun setItemForSale(@RequestBody rq: SetItemForSaleRq) {
        gameService.addItemForSale(rq.gameId, rq.merchandiseId, rq.price)
    }

}