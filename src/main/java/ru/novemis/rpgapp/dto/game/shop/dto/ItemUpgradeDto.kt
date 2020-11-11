package ru.novemis.rpgapp.dto.game.shop.dto

import ru.novemis.rpgapp.dto.game.common.dto.PriceDto

data class ItemUpgradeDto(
        val id: String,
        val lvlNum: Int,
        val skillInfluences: List<SkillInfluenceDto>,
        val prices: List<List<PriceDto>>
)