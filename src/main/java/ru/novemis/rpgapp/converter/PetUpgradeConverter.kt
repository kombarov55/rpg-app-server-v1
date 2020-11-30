package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.pet.PetTemplate
import ru.novemis.rpgapp.domain.game.pet.PetUpgrade
import ru.novemis.rpgapp.dto.game.pet.dto.PetUpgradeDto
import ru.novemis.rpgapp.dto.game.pet.form.PetUpgradeForm

@Component
class PetUpgradeConverter(
        private val skillInfluenceConverter: SkillInfluenceConverter,
        private val priceCombinationConverter: PriceCombinationConverter
) {

    fun toDomain(form: PetUpgradeForm, game: Game): PetUpgrade {
        return PetUpgrade(
                lvl = form.lvl,
                description = form.description,
                skillInfluences = form.skillInfluences.map { skillInfluenceConverter.toDomain(it) }.toMutableList(),
                prices = form.prices.map {  priceCombinationConverter.toDomain(it, game.id) }
        )
    }

    fun toDto(domain: PetUpgrade): PetUpgradeDto {
        return PetUpgradeDto(
                id = domain.id,
                lvl = domain.lvl,
                description = domain.description,
                skillInfluences = domain.skillInfluences.map { skillInfluenceConverter.toDto(it) },
                prices = domain.prices.map { priceCombinationConverter.toDto(it) }
        )
    }

}