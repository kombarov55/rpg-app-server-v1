package ru.novemis.rpgapp.dto.game.skill.dto

import ru.novemis.rpgapp.dto.game.common.dto.PriceDto


data class SkillDto(
        var id: String,
        val img: String,
        val name: String,
        val description: String,
        val prices: List<List<PriceDto>>?,
        val upgradable: Boolean?,
        val upgrades: List<SkillUpgradeDto>? = null,
        val categoryName: String? = null
)