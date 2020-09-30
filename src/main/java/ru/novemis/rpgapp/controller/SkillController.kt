package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.SkillConverter
import ru.novemis.rpgapp.converter.SkillUpgradeConverter
import ru.novemis.rpgapp.dto.game.skill.dto.SkillDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillShortDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillUpgradeDto
import ru.novemis.rpgapp.dto.game.skill.form.SkillForm
import ru.novemis.rpgapp.dto.game.skill.form.SkillUpgradeForm
import ru.novemis.rpgapp.repository.game.PriceCombinationRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SkillCategoryRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SkillRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SkillUpgradeRepository
import javax.transaction.Transactional

@RestController
open class SkillController(
        private val repository: SkillRepository,
        private val skillCategoryRepository: SkillCategoryRepository,
        private val converter: SkillConverter,
        private val skillUpgradeConverter: SkillUpgradeConverter,
        private val skillUpgradeRepository: SkillUpgradeRepository
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
    @Transactional
    open fun delete(@PathVariable("id") id: String): SkillDto {
        return repository.findById(id).orElseThrow { IllegalArgumentException() }
                .let { converter.toDto(it) }
                .also {
                    repository.deleteById(id)
                }
    }

    @PutMapping("/game/{game-id}/skill/{skill-id}/upgrade/{skill-upgrade-id}")
    @Transactional
    open fun upgradeSkillUpgrade(
            @PathVariable("game-id") gameId: String,
            @PathVariable("skill-id") skillId: String,
            @PathVariable("skill-upgrade-id") skillUpgradeId: String,
            @RequestBody form: SkillUpgradeForm
    ): SkillDto {
        val skill = repository.findById(skillId).get()
        val convertedSkillUpgrade = skillUpgradeConverter.toDomain(form, gameId)

        skill.upgrades.find { it.id == skillUpgradeId }!!.apply {
            description = convertedSkillUpgrade.description
            prices = convertedSkillUpgrade.prices
        }

        return repository.save(skill)
                .let { converter.toDto(it) }
    }

    @DeleteMapping("/skill/{skill-id}/upgrade/{id}")
    @Transactional
    open fun deleteSkillUpgrade(
            @PathVariable("skill-id") skillId: String,
            @PathVariable("id") id: String
    ): SkillDto {
        return repository.findById(skillId).get()
                .apply {
                    val upgrade = upgrades.find { it.id == id }!!
                    val maxLvl = upgrades.map { it.lvlNum }.max()

                    if (upgrade.lvlNum != maxLvl) throw IllegalArgumentException("Удалить можно только последний уровень.")

                    upgrades = upgrades.filter { it.id != id }
                    skillUpgradeRepository.delete(upgrade)
                }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

}