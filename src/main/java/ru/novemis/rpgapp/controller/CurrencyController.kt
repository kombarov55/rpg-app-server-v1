package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.game.dto.CurrencyDto
import ru.novemis.rpgapp.dto.game.form.CurrencyForm
import ru.novemis.rpgapp.service.CurrencyService

@RestController
class CurrencyController(
        private val currencyService: CurrencyService
) {

    @GetMapping("/game/{game-id}/currency")
    fun findByGameId(
            @PathVariable("game-id") gameId: String
    ): List<CurrencyDto> = currencyService.findByGameId(gameId)

    @PostMapping("/game/{game-id}/currency")
    fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody currencyForm: CurrencyForm
    ): CurrencyDto = currencyService.save(currencyForm, gameId)

    @PatchMapping("/game/{game-id}/currency/{id}")
    fun patch(
            @PathVariable("game-id") gameId: String,
            @PathVariable("id") currencyId: String,
            @RequestBody currencyForm: CurrencyForm
    ): CurrencyDto = currencyService.update(currencyForm, gameId, currencyId)

}