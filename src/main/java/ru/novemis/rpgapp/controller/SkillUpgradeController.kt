package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.SkillUpgradeConverter
import ru.novemis.rpgapp.dto.game.skill.dto.SkillUpgradeDto
import ru.novemis.rpgapp.repository.game.skillcategory.SkillRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SkillUpgradeRepository
import javax.transaction.Transactional

@RestController
open class SkillUpgradeController(
        private val repository: SkillUpgradeRepository,
        private val converter: SkillUpgradeConverter,

        private val skillRepository: SkillRepository
) {

    @DeleteMapping("/skillUpgrade/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ): SkillUpgradeDto {
        val skillUpgrade = repository.findById(id).get()
        val skill = skillUpgrade.skill!!

        skill.upgrades = skill.upgrades.filter { it.id != id }
        skillRepository.save(skill)

        repository.delete(skillUpgrade)

        return converter.toDto(skillUpgrade)
    }

}