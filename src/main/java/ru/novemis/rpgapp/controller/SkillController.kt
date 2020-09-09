package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.SkillConverter
import ru.novemis.rpgapp.dto.game.skill.dto.SkillDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillShortDto
import ru.novemis.rpgapp.dto.game.skill.form.SkillForm
import ru.novemis.rpgapp.repository.game.skillcategory.SkillCategoryRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SkillRepository
import java.lang.IllegalArgumentException
import javax.transaction.Transactional

@RestController
open class SkillController(
        private val repository: SkillRepository,
        private val skillCategoryRepository: SkillCategoryRepository,
        private val converter: SkillConverter
) {

    @GetMapping("/game/{game-id}/skill/{id}")
    open fun findById(
            @PathVariable("id") id: String
    ): SkillDto = repository.findById(id).map { converter.toDto(it) }.orElseThrow { RuntimeException() }

    @GetMapping("/skillCategory/{id}/skill")
    @Transactional
    open fun findAllBySkillCategory(
            @PathVariable("id") skillCategoryId: String
    ): List<SkillDto> {
        return repository.findBySkillCategoryId(skillCategoryId)
                .map { converter.toDto(it) }
    }

    @GetMapping("/game/{game-id}/skill/short")
    @Transactional
    open fun findByGameId(
            @PathVariable("game-id") gameId: String
    ): List<SkillShortDto> = repository
            .findByGameId(gameId)
            .map { converter.toShortDto(it) }

    @PostMapping("/skillCategory/{id}/skill")
    @Transactional
    open fun save(
            @PathVariable("id") skillCategoryId: String,
            @RequestBody form: SkillForm
    ): SkillDto {
        val skillCategory = skillCategoryRepository.findById(skillCategoryId).orElseThrow { IllegalArgumentException("$skillCategoryId is invalid") }

        return converter.toDomain(form, skillCategory)
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @PutMapping("/skill/{id}")
    open fun update(
            @PathVariable("id") id: String,
            @RequestBody form: SkillForm
    ): SkillDto {
        val prev = repository.findById(id).orElseThrow { IllegalArgumentException() }
        val updated = converter.toDomain(form, prev.skillCategory!!).apply { this.id = id }

        return repository
                .save(updated)
                .let { converter.toDto(it) }
    }

    @DeleteMapping("/skill/{id}")
    open fun delete(@PathVariable("id") id: String): SkillDto {
        return repository.findById(id).orElseThrow { IllegalArgumentException() }
                .let { converter.toDto(it) }
                .also {
                    repository.deleteById(id)
                }
    }

}