package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.SkillCategoryConverter
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryShortDto
import ru.novemis.rpgapp.dto.game.skill.form.SkillCategoryForm
import ru.novemis.rpgapp.repository.game.skillcategory.SkillCategoryRepository
import javax.transaction.Transactional

@RestController
open class SkillCategoryController(
        private val repository: SkillCategoryRepository,
        private val converter: SkillCategoryConverter
) {

    @GetMapping("/game/{id}/skillCategory")
    @Transactional
    open fun getAllByGameId(
            @PathVariable("id") gameId: String
    ): List<SkillCategoryDto> =
            repository.findAllByGameId(gameId).map { converter.toDto(it) }

    @GetMapping("/game/{id}/skillCategory/short")
    @Transactional
    open fun getAllShortByGameId(
            @PathVariable("id") gameId: String
    ): List<SkillCategoryShortDto> =
            repository.findAllByGameId(gameId).map { converter.toShortDto(it) }

    @GetMapping("/game/{id}/skillCategory/filter")
    @Transactional
    open fun getAllByGameIdAndDestination(
            @PathVariable("id") gameId: String,
            @RequestParam("destination") destination: String
    ): List<SkillCategoryDto> = repository.findAllByGameIdAndDestination(gameId, Destination.valueOf(destination)).map { converter.toDto(it) }

    @PostMapping("/game/{game-id}/skillCategory")
    @Transactional
    open fun save(
            @RequestBody form: SkillCategoryForm,
            @PathVariable("game-id") gameId: String
    ): SkillCategoryDto = converter.toDomain(form).apply { game = Game(gameId) }
            .let { repository.save(it) }
            .let { converter.toDto(it) }

    @GetMapping("/skillCategory/{id}")
    @Transactional
    open fun getById(
            @PathVariable("id") id: String
    ): SkillCategoryDto = repository.findById(id).get().let { converter.toDto(it) }

    @PutMapping("/skillCategory/{id}")
    @Transactional
    open fun update(
            @PathVariable("id") id: String,
            @RequestBody body: SkillCategoryForm
    ): SkillCategoryDto = repository.findById(id).get().apply {
                name = body.name
                img = body.img
                description = body.description
            }.let { repository.save(it) }
             .let { converter.toDto(it) }

    @DeleteMapping("/skillCategory/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ): SkillCategoryDto = repository.findById(id).get()
            .also { repository.delete(it) }
            .let { converter.toDto(it) }
}