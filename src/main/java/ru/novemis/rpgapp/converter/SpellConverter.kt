package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.SchoolLvl
import ru.novemis.rpgapp.domain.game.skill.Spell
import ru.novemis.rpgapp.dto.game.skill.dto.SpellDto
import ru.novemis.rpgapp.dto.game.skill.form.SpellForm
import ru.novemis.rpgapp.repository.game.skillcategory.SpellRepository

@Component
class SpellConverter(
        private val spellRepository: SpellRepository
) {

    fun toDomain(form: SpellForm, schoolLvl: SchoolLvl? = null): Spell {
        return Spell(
                name = form.name,
                img = form.img,
                description = form.description,
                requiredSpells = form.requiredSpells.map { spellRepository.findById(it.id!!).get() },
                schoolLvl = schoolLvl
        )
    }

    fun toDto(domain: Spell): SpellDto {
        return SpellDto(
                id = domain.id,
                img = domain.img,
                name = domain.name,
                description = domain.description,
                lvl = domain.schoolLvl!!.lvl,
                spellSchoolName = domain.schoolLvl!!.spellSchool!!.name,
                requiredSpells = domain.requiredSpells.map {
                    SpellDto(
                            id = it.id,
                            name = it.name
                    )
                }
        )
    }

}