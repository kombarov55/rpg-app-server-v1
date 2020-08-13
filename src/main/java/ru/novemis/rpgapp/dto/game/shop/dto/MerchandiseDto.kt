package ru.novemis.rpgapp.dto.game.shop.dto

import ru.novemis.rpgapp.dto.game.common.dto.PriceDto

data class MerchandiseDto(
        val id: String,
        val name: String,
        val img: String,
        val categoryName: String,
        val typeName: String,
        val slots: Int,
        val prices: List<List<PriceDto>>,
        val skillInfluences: List<SkillInfluenceDto>
)