package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.SkillUpgradeConverter
import ru.novemis.rpgapp.dto.game.skill.dto.SkillUpgradeDto
import ru.novemis.rpgapp.dto.game.skill.form.SkillUpgradeForm
import ru.novemis.rpgapp.repository.game.skillcategory.SkillRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SkillUpgradeRepository
import javax.transaction.Transactional

@RestController
open class SkillUpgradeController(
        private val repository: SkillUpgradeRepository,
        private val converter: SkillUpgradeConverter,

        private val skillRepository: SkillRepository
) {

    @PostMapping("/skill/{skill-id}/skillUpgrade")
    @Transactional
    open fun addSkillUpgradeToSkill(
            @PathVariable("skill-id") skillId: String,
            @RequestBody form: SkillUpgradeForm
    ): SkillUpgradeDto {
        val skill = skillRepository.findById(skillId).get()

        return converter.toDomain(form, skill.skillCategory!!.game!!.id).apply {
            this.skill = skill
        }.let { repository.save(it) }.let { converter.toDto(it) }
    }

    @PutMapping("/skillUpgrade/{id}")
    @Transactional
    open fun upgradeSkillUpgrade(
            @PathVariable("id") id: String,
            @RequestBody form: SkillUpgradeForm
    ): SkillUpgradeDto {
        val savedEntity = repository.findById(id).get()
        val skill = savedEntity.skill!!
        val convertedSkillUpgrade = converter.toDomain(form, skill.skillCategory!!.game!!.id)

        savedEntity.apply {
            description = convertedSkillUpgrade.description
            prices = convertedSkillUpgrade.prices
        }

        return repository.save(savedEntity)
                .let { converter.toDto(it) }
    }

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