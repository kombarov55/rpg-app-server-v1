package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.SkillConverter
import ru.novemis.rpgapp.repository.game.skillcategory.SkillRepository

@RestController
class SkillController(
        private val repository: SkillRepository,
        private val converter: SkillConverter
) {
}