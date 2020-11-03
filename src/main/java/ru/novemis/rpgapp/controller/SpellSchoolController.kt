package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.SpellSchoolConverter
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.dto.game.skill.dto.SpellSchoolDto
import ru.novemis.rpgapp.dto.game.skill.form.SpellSchoolForm
import ru.novemis.rpgapp.repository.game.skillcategory.SkillCategoryRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SpellSchoolRepository
import javax.transaction.Transactional

@RestController
open class SpellSchoolController(
        private val repository: SpellSchoolRepository,
        private val converter: SpellSchoolConverter,

        private val skillCategoryRepository: SkillCategoryRepository
) {

    @GetMapping("/game/{id}/skillCategory/findByDestination")
    @Transactional
    open fun findByGameIdAndDestination(
            @PathVariable("id") gameId: String,
            @RequestParam("destination") destination: String
    ): List<SpellSchoolDto> {
        return repository.findBySkillCategoryGameIdAndSkillCategoryDestination(gameId, Destination.valueOf(destination))
                .map { converter.toDto(it) }
    }

    @PostMapping("/skillCategory/{skill-category-id}/spellSchool")
    @Transactional
    open fun addSpellSchoolToSkillCategory(
            @PathVariable("skill-category-id") skillCategoryId: String,
            @RequestBody form: SpellSchoolForm
    ): SpellSchoolDto {
        val skillCategory = skillCategoryRepository.findById(skillCategoryId).get()

        val spellSchool = converter.toDomain(form, skillCategory.game!!.id).apply { this.skillCategory = skillCategory }

        return repository.save(spellSchool)
                .let { converter.toDto(it) }
    }

    @PutMapping("/spellSchool/{id}")
    @Transactional
    open fun editSpellSchool(
            @PathVariable("id") id: String,
            @RequestBody form: SpellSchoolForm
    ): SpellSchoolDto {
        val savedEntity = repository.findById(id).get()
        val convertedEntity = converter.toDomain(form, savedEntity.skillCategory!!.game!!.id)

        return convertedEntity.apply {
            this.id = savedEntity.id
            schoolLvls = savedEntity.schoolLvls
            skillCategory = savedEntity.skillCategory
        }.let { repository.save(it) }.let { converter.toDto(it) }
    }
}
