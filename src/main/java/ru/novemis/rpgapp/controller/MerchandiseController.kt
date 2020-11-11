package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.MerchandiseConverter
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseDto
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseShortDto
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseForm
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.shop.MerchandiseRepository
import javax.transaction.Transactional

@RestController
open class MerchandiseController(
        private val repository: MerchandiseRepository,
        private val converter: MerchandiseConverter,
        private val characterRepository: GameCharacterRepository
) {

    @GetMapping("/game/{game-id}/merchandise")
    @Transactional
    open fun getAll(
            @PathVariable("game-id") gameId: String
    ): List<MerchandiseDto> {
        return repository.findAllByGameId(gameId).map { converter.toDto(it) }
    }

    @PostMapping("/game/{game-id}/merchandise")
    @Transactional
    open fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody form: MerchandiseForm
    ): MerchandiseDto = form
            .let { converter.toDomain(form, gameId) }
            .let { repository.save(it) }
            .let { converter.toDto(it) }

    @PutMapping("/game/{game-id}/merchandise/{id}")
    @Transactional
    open fun update(
            @PathVariable("game-id") gameId: String,
            @PathVariable("id") id: String,
            @RequestBody form: MerchandiseForm
    ): MerchandiseDto = form
            .let { converter.toDomain(form, gameId) }
            .also { it.id = id }
            .let { repository.save(it) }
            .let { converter.toDto(it) }

    @DeleteMapping("/game/{game-id}/merchandise/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ) = repository.deleteById(id)

    @GetMapping("/game/{game-id}/merchandise/filter")
    @Transactional
    open fun findByGameIdAndDestination(
            @PathVariable("game-id") gameId: String,
            @RequestParam("destination") destinationsDelimited: String
    ): List<MerchandiseDto> {
        val destinations = destinationsDelimited.split(",").map { Destination.valueOf(it) }

        return repository.findAllByGameIdAndDestination(gameId, destinations)
                .map { converter.toDto(it) }
    }

    @GetMapping("/game/{game-id}/merchandise/filterByName")
    @Transactional
    open fun findByGameIdAndName(
            @PathVariable("game-id") gameId: String,
            @RequestParam("name") name: String
    ): List<MerchandiseDto> {
        return repository.findByGameIdAndNameStartsWith(gameId, name)
                .map { converter.toDto(it) }
    }

    @GetMapping("/character/{id}/ownedMerchandise/short")
    @Transactional
    open fun getOwnedMerchandise(
            @PathVariable("id") characterId: String
    ): List<MerchandiseShortDto> {
        return characterRepository.findById(characterId).get().ownedMerchandise.map { converter.toShortDto(it) }
    }
}