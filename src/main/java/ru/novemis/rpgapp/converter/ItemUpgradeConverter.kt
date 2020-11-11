package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.ItemUpgrade
import ru.novemis.rpgapp.dto.game.shop.dto.ItemUpgradeDto
import ru.novemis.rpgapp.dto.game.shop.form.ItemUpgradeForm

@Component
class ItemUpgradeConverter(
        private val skillInfluenceConverter: SkillInfluenceConverter,
        private val priceCombinationConverter: PriceCombinationConverter
) {

    fun toDomain(form: ItemUpgradeForm, gameId: String): ItemUpgrade {
        return ItemUpgrade(
                lvlNum = form.lvlNum,
                skillInfluences = form.skillInfluences.map { skillInfluenceConverter.toDomain(it) },
                prices = form.prices.map { priceCombinationConverter.toDomain(it, gameId) }
        )
    }

    fun toDto(domain: ItemUpgrade): ItemUpgradeDto {
        return ItemUpgradeDto(
                id = domain.id,
                lvlNum = domain.lvlNum,
                skillInfluences = domain.skillInfluences.map { skillInfluenceConverter.toDto(it) },
                prices = domain.prices.map { priceCombinationConverter.toDto(it) }
        )
    }
}