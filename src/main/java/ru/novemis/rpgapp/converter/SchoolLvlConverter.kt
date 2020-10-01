package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.SchoolLvl
import ru.novemis.rpgapp.dto.game.skill.dto.SchoolLvlDto

@Component
class SchoolLvlConverter(
        private val spellConverter: SpellConverter,
        private val spellPurchaseOptionConverter: SpellPurchaseOptionConverter
) {

    fun toDto(domain: SchoolLvl): SchoolLvlDto {
        return SchoolLvlDto(
                id = domain.id,
                lvl = domain.lvl,
                spellPurchaseOptions = domain.spellPurchaseOptions.map { spellPurchaseOptionConverter.toDto(it) },
                spells = domain.spells.map { spellConverter.toDto(it) }
        )
    }

}