package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.game.CurrencyDto
import ru.novemis.rpgapp.dto.game.CurrencyForm
import ru.novemis.rpgapp.service.CurrencyService

@RestController
class CurrencyController(
        private val currencyService: CurrencyService
) {

    @GetMapping("/game/{id}/currency")
    fun getByGameId(@PathVariable("id") gameId: String): List<CurrencyDto> = currencyService.findByGameId(gameId)

    @PostMapping("/game/{id}/currency")
    fun save(
            @RequestBody currencyForm: CurrencyForm,
            @PathVariable("id") gameId: String
    ): CurrencyDto = currencyService.save(currencyForm, gameId)

    @PatchMapping("/game/{game-id}/currency/{currency-id}")
    fun patch(
            @RequestBody currencyForm: CurrencyForm,
            @PathVariable("game-id") gameId: String,
            @PathVariable("currency-id") currencyId: String
    ): CurrencyDto = currencyService.update(currencyForm, gameId, currencyId)

}