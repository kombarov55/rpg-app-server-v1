package ru.novemis.rpgapp.dto.game.skill.dto

import ru.novemis.rpgapp.dto.game.common.dto.PriceDto

data class SkillUpgradeDto(
        val lvlNum: Int,
        val description: String,
        val prices: List<List<PriceDto>>
)