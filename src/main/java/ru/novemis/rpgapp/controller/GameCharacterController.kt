package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.GameCharacterConverter
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterDto
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import javax.transaction.Transactional

@RestController
open class GameCharacterController(
        private val repository: GameCharacterRepository,
        private val converter: GameCharacterConverter
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
        return repository.findByUserId(userId).map { converter.toShortDto(it) }
    }

}