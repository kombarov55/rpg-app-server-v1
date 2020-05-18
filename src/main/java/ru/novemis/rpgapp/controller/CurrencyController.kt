package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dto.game.CurrencyDto
import ru.novemis.rpgapp.service.CurrencyService

@RestController
class CurrencyController(
        private val currencyService: CurrencyService
) {

    @GetMapping("/game/{id}/currency")
    fun getByGameId(@PathVariable("id") gameId: String): List<CurrencyDto> {
        return currencyService.findByGameId(gameId)
    }

}