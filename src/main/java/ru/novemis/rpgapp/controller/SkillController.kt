package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.SkillConverter
import ru.novemis.rpgapp.dto.game.skill.dto.SkillDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillShortDto
import ru.novemis.rpgapp.repository.game.skillcategory.SkillRepository
import javax.transaction.Transactional

@RestController
open class SkillController(
        private val repository: SkillRepository,
        private val converter: SkillConverter
) {

    @GetMapping("/game/{game-id}/skill/{id}")
    open fun findById(
            @PathVariable("id") id: String
    ): SkillDto = repository.findById(id).map { converter.toDto(it) }.orElseThrow { RuntimeException() }

    @GetMapping("/game/{game-id}/skill/short")
    @Transactional
    open fun findByGameId(
            @PathVariable("game-id") gameId: String
    ): List<SkillShortDto> = repository
            .findByGameId(gameId)
            .map { converter.toShortDto(it) }

}