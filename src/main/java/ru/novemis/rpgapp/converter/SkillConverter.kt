package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.Skill
import ru.novemis.rpgapp.domain.game.skill.SkillUpgrade
import ru.novemis.rpgapp.dto.game.skill.dto.SkillDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillShortDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillUpgradeDto
import ru.novemis.rpgapp.dto.game.skill.form.SkillForm

@Component
class SkillConverter(
        private val priceCombinationConverter: PriceCombinationConverter
) {

    fun toDomain(form: SkillForm, gameId: String): Skill {
        return Skill().apply {
            val skill = this

            name = form.name
            description = form.description
            img = form.img
            prices = form.prices.map { listOfPrices -> priceCombinationConverter.toDomain(listOfPrices, gameId) }
            upgradable = form.upgradable
            this.skillCategory = skillCategory

            upgrades = form.skillUpgrades.map { skillUpgradeForm ->
                SkillUpgrade(
                        lvlNum = skillUpgradeForm.lvlNum,
                        description = skillUpgradeForm.description,
                        prices = skillUpgradeForm.prices.map { listOfPrices -> priceCombinationConverter.toDomain(listOfPrices, gameId) },
                        skill = skill
                )
            }
        }
    }

    fun toDto(domain: Skill): SkillDto {
        return SkillDto(
                id = domain.id,
                img = domain.img,
                name = domain.name,
                description = domain.description,
                prices = domain.prices.map { priceCombinationConverter.toDto(it) },
                upgradable = domain.upgradable,
                upgrades = domain.upgrades.map {
                    SkillUpgradeDto(
                            lvlNum = it.lvlNum,
                            description = it.description,
                            prices = it.prices.map { priceCombinationConverter.toDto(it) }
                    )
                }
        )
    }

    fun toShortDto(domain: Skill): SkillShortDto {
        return SkillShortDto(
                id = domain.id,
                name = domain.name
        )
    }

}