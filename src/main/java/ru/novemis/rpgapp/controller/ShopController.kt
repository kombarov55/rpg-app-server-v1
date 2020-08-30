package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.service.ShopService

@RestController
class ShopController(
        private val shopService: ShopService
) {

    @PostMapping("/game/{game-id}/shop")
    fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody shopForm: ShopForm
    ): ShopDto = shopService.save(shopForm, gameId)

    @PutMapping("/game/{game-id}/shop/{shop-id}")
    fun update(
            @PathVariable("game-id") gameId: String,
            @PathVariable("shop-id") shopId: String,
            @RequestBody form: ShopForm
    ): ShopDto = shopService.update(form, gameId, shopId)

}