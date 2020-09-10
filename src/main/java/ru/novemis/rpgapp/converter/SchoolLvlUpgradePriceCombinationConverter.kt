package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.SchoolLvlUpgradePriceCombination
import ru.novemis.rpgapp.dto.game.skill.dto.SchoolLvlUpgradePriceCombinationDto
import ru.novemis.rpgapp.dto.game.skill.form.SchoolLvlUpgradePriceCombinationForm

@Component
class SchoolLvlUpgradePriceCombinationConverter(
        private val priceCombinationConverter: PriceCombinationConverter
) {

    fun toDomain(form: SchoolLvlUpgradePriceCombinationForm, gameId: String): SchoolLvlUpgradePriceCombination {
        return SchoolLvlUpgradePriceCombination().apply {
            spellCount = form.spellCount
            priceCombinations = form.priceCombinationList.map { priceCombinationConverter.toDomain(it, gameId) }
        }
    }

    fun toDto(domain: SchoolLvlUpgradePriceCombination): SchoolLvlUpgradePriceCombinationDto {
        return SchoolLvlUpgradePriceCombinationDto(
                id = domain.id,
                spellCount = domain.spellCount,
                priceCombinations = domain.priceCombinations.map { priceCombinationConverter.toDto(it) }
        )
    }

}