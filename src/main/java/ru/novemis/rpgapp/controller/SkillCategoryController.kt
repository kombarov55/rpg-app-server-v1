package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dto.game.skill.form.SkillCategoryForm
import ru.novemis.rpgapp.service.SkillCategoryService

@RestController
class SkillCategoryController(
        private val skillCategoryService: SkillCategoryService
) {

    @PostMapping("/game/{game-id}/skillCategory")
    fun save(
            @RequestBody skillCategoryForm: SkillCategoryForm,
            @PathVariable("game-id") gameId: String
    ) {
        skillCategoryService.save(skillCategoryForm, gameId)
    }

}