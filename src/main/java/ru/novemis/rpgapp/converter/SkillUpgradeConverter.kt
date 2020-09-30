package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.SkillUpgrade
import ru.novemis.rpgapp.dto.game.skill.dto.SkillUpgradeDto
import ru.novemis.rpgapp.dto.game.skill.form.SkillUpgradeForm

@Component
class SkillUpgradeConverter(
        private val priceCombinationConverter: PriceCombinationConverter
) {

    fun toDomain(form: SkillUpgradeForm, gameId: String): SkillUpgrade {
        return SkillUpgrade(
                lvlNum = form.lvlNum,
                description = form.description,
                prices = form.prices.map { priceCombinationConverter.toDomain(it, gameId) }
        )
    }

    fun toDto(domain: SkillUpgrade): SkillUpgradeDto {
        return SkillUpgradeDto(
                id = domain.id,
                lvlNum = domain.lvlNum,
                description = domain.description,
                prices = domain.prices.map { priceCombinationConverter.toDto(it) }
        )
    }

}