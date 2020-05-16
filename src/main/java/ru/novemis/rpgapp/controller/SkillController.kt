package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dto.game.skill.SkillForm
import ru.novemis.rpgapp.dto.game.skill.SkillDto
import ru.novemis.rpgapp.service.SkillService

@RestController
class SkillController(
        private val skillService: SkillService
) {

    @GetMapping("/game/{game-id}/skill")
    fun findByGameId(@PathVariable("game-id") gameId: String): List<SkillDto> {
        return skillService.findByGameId(gameId)
    }

    @PostMapping("/skill")
    fun save(@RequestBody skillForm: SkillForm): SkillDto {
        return skillService.save(skillForm)
    }

    @PutMapping("/skill/{skill-id}")
    fun update(@PathVariable("skill-id") id: String,
               @RequestBody form: SkillForm): SkillDto {
        return skillService.update(id, form)
    }
}