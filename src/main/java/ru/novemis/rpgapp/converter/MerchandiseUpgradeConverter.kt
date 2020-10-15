package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.MerchandiseUpgrade
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseUpgradeDto
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseUpgradeForm

@Component
class MerchandiseUpgradeConverter(
        private val skillInfluenceConverter: SkillInfluenceConverter,
        private val priceCombinationConverter: PriceCombinationConverter
) {

    fun toDomain(form: MerchandiseUpgradeForm, gameId: String): MerchandiseUpgrade {
        return MerchandiseUpgrade(
                lvlNum = form.lvlNum,
                skillInfluences = form.skillInfluences.map { skillInfluenceConverter.toDomain(it) },
                prices = form.purchasePrices.map { priceCombinationConverter.toDomain(it, gameId) }
        )
    }

    fun toDto(domain: MerchandiseUpgrade): MerchandiseUpgradeDto {
        return MerchandiseUpgradeDto(
                id = domain.id,
                lvlNum = domain.lvlNum,
                skillInfluences = domain.skillInfluences.map { skillInfluenceConverter.toDto(it) },
                purchasePrices = domain.prices.map { priceCombinationConverter.toDto(it) }
        )
    }
}