package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.game.ConversionDto
import ru.novemis.rpgapp.dto.game.ConversionForm
import ru.novemis.rpgapp.service.MyConversionService

@RestController
class ConversionController(
        private val conversionService: MyConversionService
) {

    @PostMapping("/game/{id}/conversion")
    fun save(@PathVariable("id") gameId: String,
             @RequestBody form: ConversionForm): ConversionDto {
        return conversionService.save(gameId, form)
    }

    @GetMapping("/game/{id}/conversion")
    fun findByGameId(@PathVariable("id") gameId: String): List<ConversionDto> {
        return conversionService.findByGameId(gameId)
    }

}