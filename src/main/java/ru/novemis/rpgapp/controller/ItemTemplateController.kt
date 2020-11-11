package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.ItemTemplateConverter
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.dto.game.shop.dto.ItemTemplateDto
import ru.novemis.rpgapp.dto.game.shop.form.ItemTemplateForm
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.shop.ItemTemplateRepository
import javax.transaction.Transactional

@RestController
open class ItemTemplateController(
        private val repository: ItemTemplateRepository,
        private val converter: ItemTemplateConverter
) {

    @GetMapping("/game/{game-id}/itemTemplate")
    @Transactional
    open fun getAll(
            @PathVariable("game-id") gameId: String
    ): List<ItemTemplateDto> {
        return repository.findAllByGameId(gameId).map { converter.toDto(it) }
    }

    @PostMapping("/game/{game-id}/itemTemplate")
    @Transactional
    open fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody form: ItemTemplateForm
    ): ItemTemplateDto = form
            .let { converter.toDomain(form, gameId) }
            .let { repository.save(it) }
            .let { converter.toDto(it) }

    @PutMapping("/game/{game-id}/itemTemplate/{id}")
    @Transactional
    open fun update(
            @PathVariable("game-id") gameId: String,
            @PathVariable("id") id: String,
            @RequestBody form: ItemTemplateForm
    ): ItemTemplateDto = form
            .let { converter.toDomain(form, gameId) }
            .also { it.id = id }
            .let { repository.save(it) }
            .let { converter.toDto(it) }

    @DeleteMapping("/game/{game-id}/itemTemplate/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ) = repository.deleteById(id)

    @GetMapping("/game/{game-id}/itemTemplate/filter")
    @Transactional
    open fun findByGameIdAndDestination(
            @PathVariable("game-id") gameId: String,
            @RequestParam("destination") destinationsDelimited: String
    ): List<ItemTemplateDto> {
        val destinations = destinationsDelimited.split(",").map { Destination.valueOf(it) }

        return repository.findAllByGameIdAndDestination(gameId, destinations)
                .map { converter.toDto(it) }
    }

    @GetMapping("/game/{game-id}/itemTemplate/filterByName")
    @Transactional
    open fun findByGameIdAndName(
            @PathVariable("game-id") gameId: String,
            @RequestParam("name") name: String
    ): List<ItemTemplateDto> {
        return repository.findByGameIdAndNameStartsWith(gameId, name)
                .map { converter.toDto(it) }
    }
}