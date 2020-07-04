package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
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

}