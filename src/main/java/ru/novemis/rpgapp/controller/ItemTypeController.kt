package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.ItemTypeConverter
import ru.novemis.rpgapp.dto.game.shop.dto.ItemTypeDto
import ru.novemis.rpgapp.dto.game.shop.form.ItemTypeForm
import ru.novemis.rpgapp.repository.game.shop.ItemTypeRepository

@RestController
class ItemTypeController(
        private val repository: ItemTypeRepository,
        private val converter: ItemTypeConverter
) {

    @GetMapping("/game/{game-id}/itemType")
    fun findByGameId(
            @PathVariable("game-id") gameId: String
    ): List<ItemTypeDto> = repository.findByGameId(gameId).map { converter.toDto(it) }

    @PostMapping("/game/{game-id}/itemType")
    fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody form: ItemTypeForm
    ): ItemTypeDto = form
            .let { converter.toDomain(it, gameId) }
            .let { repository.save(it) }
            .let { converter.toDto(it) }

    @PutMapping("/game/{game-id}/itemType/{id}")
    fun update(
            @PathVariable("game-id") gameId: String,
            @PathVariable("id") id: String,
            @RequestBody form: ItemTypeForm
    ): ItemTypeDto = form
            .let { converter.toDomain(it, gameId) }
            .also { it.id = id }
            .let { repository.save(it) }
            .let { converter.toDto(it) }

    @DeleteMapping("/game/{game-id}/itemType/{id}")
    fun delete(
            @PathVariable("game-id") gameId: String,
            @PathVariable("id") id: String
    ) = repository.deleteById(id)
}