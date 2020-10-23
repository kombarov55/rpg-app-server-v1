package ru.novemis.rpgapp.dto.game.skill.dto

import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.dto.game.common.dto.PriceDto

data class SkillShortDto(
        val id: String,
        val name: String,
        val img: String,
        val destination: Destination,
        val prices: List<List<PriceDto>>? = null,
        val categoryName: String? = null
)