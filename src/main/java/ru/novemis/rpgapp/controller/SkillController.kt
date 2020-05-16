package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dto.game.SkillForm
import ru.novemis.rpgapp.dto.game.skill.SkillShortDto
import ru.novemis.rpgapp.service.SkillService

@RestController
@RequestMapping("/skill")
class SkillController(
        private val skillService: SkillService
) {

    @GetMapping("/{game-id}")
    fun findByGameId(@PathVariable("game-id") gameId: String): List<SkillShortDto> {
        return skillService.findByGameId(gameId)
    }

    @PostMapping
    fun save(@RequestBody skillForm: SkillForm): SkillShortDto {
        return skillService.save(skillForm)
    }

}