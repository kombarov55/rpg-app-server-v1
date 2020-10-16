package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryShortDto
import ru.novemis.rpgapp.dto.game.skill.form.SkillCategoryForm
import ru.novemis.rpgapp.service.SkillCategoryService
import javax.transaction.Transactional

@RestController
open class SkillCategoryController(
        private val skillCategoryService: SkillCategoryService
) {

    @GetMapping("/game/{id}/skillCategory")
    @Transactional
    open fun getAllByGameId(@PathVariable("id") gameId: String): List<SkillCategoryDto> =
            skillCategoryService.findAllByGameId(gameId)

    @GetMapping("/game/{id}/skillCategory/short")
    @Transactional
    open fun getAllShortByGameId(@PathVariable("id") gameId: String): List<SkillCategoryShortDto> =
            skillCategoryService.findAllShortByGameId(gameId)

    @PostMapping("/game/{game-id}/skillCategory")
    @Transactional
    open fun save(
            @RequestBody skillCategoryForm: SkillCategoryForm,
            @PathVariable("game-id") gameId: String
    ): SkillCategoryDto =
            skillCategoryService.save(skillCategoryForm, gameId)

    @GetMapping("/skillCategory/{id}")
    fun getById(@PathVariable("id") id: String): SkillCategoryDto = skillCategoryService.findById(id)

    @PutMapping("/skillCategory/{id}")
    @Transactional
    open fun update(
            @PathVariable("id") id: String,
            @RequestBody body: SkillCategoryForm
    ): SkillCategoryDto = skillCategoryService.update(id, body)

    @DeleteMapping("/skillCategory/{id}")
    @Transactional
    open fun delete(@PathVariable("id") id: String): SkillCategoryDto = skillCategoryService.deleteById(id)


}