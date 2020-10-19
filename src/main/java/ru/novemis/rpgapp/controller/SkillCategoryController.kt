package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryShortDto
import ru.novemis.rpgapp.dto.game.skill.form.SkillCategoryForm
import ru.novemis.rpgapp.service.SkillCategoryService
import javax.transaction.Transactional

@RestController
open class SkillCategoryController(
        private val service: SkillCategoryService
) {

    @GetMapping("/game/{id}/skillCategory")
    @Transactional
    open fun getAllByGameId(@PathVariable("id") gameId: String): List<SkillCategoryDto> =
            service.findAllByGameId(gameId)

    @GetMapping("/game/{id}/skillCategory/short")
    @Transactional
    open fun getAllShortByGameId(@PathVariable("id") gameId: String): List<SkillCategoryShortDto> =
            service.findAllShortByGameId(gameId)

    @GetMapping("/game/{id}/skillCategory/filter")
    open fun getAllByGameIdAndDestination(
            @PathVariable("id") gameId: String,
            @RequestParam("destination") destination: String
    ): List<SkillCategoryDto> {
        return service.findAllByGameIdAndDestination(gameId, Destination.valueOf(destination))
    }

    @PostMapping("/game/{game-id}/skillCategory")
    @Transactional
    open fun save(
            @RequestBody skillCategoryForm: SkillCategoryForm,
            @PathVariable("game-id") gameId: String
    ): SkillCategoryDto =
            service.save(skillCategoryForm, gameId)

    @GetMapping("/skillCategory/{id}")
    fun getById(@PathVariable("id") id: String): SkillCategoryDto = service.findById(id)

    @PutMapping("/skillCategory/{id}")
    @Transactional
    open fun update(
            @PathVariable("id") id: String,
            @RequestBody body: SkillCategoryForm
    ): SkillCategoryDto = service.update(id, body)

    @DeleteMapping("/skillCategory/{id}")
    @Transactional
    open fun delete(@PathVariable("id") id: String): SkillCategoryDto = service.deleteById(id)


}