package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.SpellSchool
import ru.novemis.rpgapp.dto.game.skill.dto.SpellSchoolDto
import ru.novemis.rpgapp.dto.game.skill.form.SpellSchoolForm

@Component
class SpellSchoolConverter(
        private val priceCombinationConverter: PriceCombinationConverter,
        private val schoolLvlConverter: SchoolLvlConverter
) {

    fun toDomain(form: SpellSchoolForm, gameId: String): SpellSchool {
        return SpellSchool(
                name = form.name,
                img = form.img,
                description = form.description,
                minSpellCountToUpgrade = form.minSpellCountToUpgrade,
                purchasePriceCombinations = form.purchasePriceCombinations.map { priceCombinationConverter.toDomain(it, gameId) }
        )
    }

    fun toDto(domain: SpellSchool): SpellSchoolDto {
        return SpellSchoolDto(
                id = domain.id,
                img = domain.img,
                name = domain.name,
                description = domain.description,
                minSpellCountToUpgrade = domain.minSpellCountToUpgrade,
                schoolLvls = domain.schoolLvls.map { schoolLvlConverter.toDto(it) } ,
                purchasePriceCombinations = domain.purchasePriceCombinations.map { priceCombinationConverter.toDto(it) }
        )
    }

}