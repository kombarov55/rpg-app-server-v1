package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.Skill
import ru.novemis.rpgapp.domain.game.skill.SkillCategory
import ru.novemis.rpgapp.dto.game.skill.dto.SkillDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillShortDto
import ru.novemis.rpgapp.dto.game.skill.form.SkillForm

@Component
class SkillConverter(
        private val skillUpgradeConverter: SkillUpgradeConverter,
        private val priceCombinationConverter: PriceCombinationConverter
) {

    fun toDomain(form: SkillForm, gameId: String, skillCategory: SkillCategory): Skill {
        return Skill().apply {
            name = form.name
            description = form.description
            img = form.img
            prices = form.prices.map { listOfPrices -> priceCombinationConverter.toDomain(listOfPrices, gameId) }
            upgradable = form.upgradable
            this.skillCategory = skillCategory
        }
    }

    fun toDomain(form: SkillForm, skillCategory: SkillCategory): Skill =
            toDomain(form,
                    skillCategory.game?.id ?: throw IllegalArgumentException(),
                    skillCategory
            )

    fun toDto(domain: Skill): SkillDto {
        return SkillDto(
                id = domain.id,
                img = domain.img,
                name = domain.name,
                description = domain.description,
                prices = domain.prices.map { priceCombinationConverter.toDto(it) },
                upgradable = domain.upgradable,
                upgrades = domain.upgrades.map { skillUpgradeConverter.toDto(it) }
        )
    }

    fun toShortDto(domain: Skill): SkillShortDto {
        return SkillShortDto(
                id = domain.id,
                name = domain.name,
                img = domain.img,
                destination = domain.skillCategory!!.destination!!,
                prices = domain.prices.map { priceCombinationConverter.toDto(it) },
                categoryName = domain.skillCategory!!.name
        )
    }

}