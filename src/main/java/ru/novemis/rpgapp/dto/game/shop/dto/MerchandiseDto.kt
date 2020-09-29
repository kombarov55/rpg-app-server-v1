package ru.novemis.rpgapp.dto.game.shop.dto

import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.dto.game.common.dto.PriceDto

data class MerchandiseDto(
        val id: String,
        val name: String,
        val img: String,
        val description: String,
        val category: MerchandiseCategoryDto,
        val type: MerchandiseTypeDto,
        val slots: Int,
        val skillInfluences: List<SkillInfluenceDto>,
        val destination: Destination,
        val merchandiseUpgrades: List<MerchandiseUpgradeDto>
)