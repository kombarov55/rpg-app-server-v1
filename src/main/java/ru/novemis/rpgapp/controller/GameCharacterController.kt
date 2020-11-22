package ru.novemis.rpgapp.controller

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.GameCharacterConverter
import ru.novemis.rpgapp.converter.ItemConverter
import ru.novemis.rpgapp.converter.PriceCombinationConverter
import ru.novemis.rpgapp.dto.game.character.dto.BalanceDto
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterDto
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto
import ru.novemis.rpgapp.dto.game.shop.dto.ItemShortDto
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.service.GameCharacterService
import javax.transaction.Transactional

@RestController
open class GameCharacterController(
        private val repository: GameCharacterRepository,
        private val converter: GameCharacterConverter,
        private val service: GameCharacterService,
        private val priceConverter: PriceCombinationConverter,
        private val itemConverter: ItemConverter
) {

    @GetMapping("/character/{id}")
    @Transactional
    open fun getById(
            @PathVariable("id") id: String
    ): GameCharacterDto {
        return repository.findById(id).get()
                .let { converter.toDto(it) }
    }

    @GetMapping("/userAccount/{user-id}/character")
    @Transactional
    open fun getCharacters(
            @PathVariable("user-id") userId: Long
    ): List<GameCharacterShortDto> {
        return repository.findByOwnerUserId(userId).map { converter.toShortDto(it) }
    }

    @GetMapping("/game/{id}/character/filter")
    @Transactional
    open fun findCharacterByName(
            @PathVariable("id") gameId: String,
            @RequestParam("name") name: String
    ): List<GameCharacterShortDto> {
        return repository.findByGameIdAndNameStartsWith(gameId, name, PageRequest.of(0, 5)).map { character ->
            GameCharacterShortDto(
                    id = character.id,
                    name = character.name,
                    balanceId = character.balance!!.id,
                    balance = character.balance!!.amounts.map { priceConverter.toDto(it) }
            )
        }
    }

    @GetMapping("/character/{id}/balances")
    @Transactional
    open fun getBalances(
            @PathVariable("id") characterId: String
    ): List<BalanceDto> {
        return service.getBalances(characterId)
    }

    @GetMapping("/character/{id}/items/short")
    @Transactional
    open fun getItemsShort(
            @PathVariable("id") id: String
    ): List<ItemShortDto> {
        return repository.findById(id).get().items.map { itemConverter.toShortDto(it) }
    }

    @GetMapping("/userAccount/{user-id}game/{game-id}/characters")
    open fun getCharactersByGameIdAndUserId(
            @PathVariable("user-id") userId: Long,
            @PathVariable("game-id") gameId: String
    ): List<GameCharacterShortDto> {
        return repository.findByGameIdAndOwnerUserId(gameId, userId).map { converter.toShortDto(it) }
    }

}