package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.Spell
import ru.novemis.rpgapp.dto.game.skill.dto.SpellDto
import ru.novemis.rpgapp.dto.game.skill.form.SpellForm

@Component
class SpellConverter {

    fun toDomain(form: SpellForm): Spell {
        return Spell(
                name = form.name,
                img = form.img,
                description = form.description
        )
    }

    fun toDto(domain: Spell): SpellDto {
        return SpellDto(
                id = domain.id,
                img = domain.img,
                name = domain.name,
                description = domain.description,
                lvl = domain.schoolLvl!!.lvl,
                spellSchoolName = domain.schoolLvl!!.spellSchool!!.name
        )
    }

}