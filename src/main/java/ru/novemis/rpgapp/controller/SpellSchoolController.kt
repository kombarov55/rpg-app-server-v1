package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.SpellSchoolConverter
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
}
