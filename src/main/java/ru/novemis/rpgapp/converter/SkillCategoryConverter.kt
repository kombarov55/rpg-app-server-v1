package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.SkillCategory
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryShortDto
import ru.novemis.rpgapp.dto.game.skill.form.SkillCategoryForm

@Component
class SkillCategoryConverter(
        private val skillConverter: SkillConverter,
        private val spellSchoolConverter: SpellSchoolConverter
) {

    fun toDomain(form: SkillCategoryForm): SkillCategory {

        return SkillCategory(
                name = form.name,
                description = form.description,
                img = form.img,
                complex = form.complex,
                destination = form.destination
        )
    }

    fun toDto(domain: SkillCategory): SkillCategoryDto {
        return SkillCategoryDto(
                id = domain.id,
                img = domain.img,
                name = domain.name,
                description = domain.description,
                complex = domain.complex,
                skills = domain.skills.map { skillConverter.toDto(it) },
                spellSchools = domain.spellSchools.map { spellSchoolConverter.toDto(it) },
                destination = domain.destination,
                gameId = domain.game?.id!!
        )
    }

    fun toShortDto(domain: SkillCategory): SkillCategoryShortDto {
        return SkillCategoryShortDto(
                id = domain.id,
                name = domain.name,
                description = domain.description,
                img = domain.img,
                destination = domain.destination!!.name
        )
    }

}