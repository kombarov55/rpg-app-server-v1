package ru.novemis.rpgapp.dto.game.shop.dto

import ru.novemis.rpgapp.domain.game.shop.MerchandiseDestination
import ru.novemis.rpgapp.dto.game.common.dto.PriceDto

data class MerchandiseDto(
        val id: String,
        val name: String,
        val img: String,
        val description: String,
        val category: MerchandiseCategoryDto,
        val type: MerchandiseTypeDto,
        val slots: Int,
        val prices: List<List<PriceDto>>,
        val skillInfluences: List<SkillInfluenceDto>,
        val destination: MerchandiseDestination,
        val merchandiseUpgrades: List<MerchandiseUpgradeDto>
)