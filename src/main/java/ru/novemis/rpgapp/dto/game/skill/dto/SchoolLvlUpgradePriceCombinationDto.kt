package ru.novemis.rpgapp.dto.game.skill.dto

import ru.novemis.rpgapp.dto.game.common.dto.PriceDto

class SchoolLvlUpgradePriceCombinationDto(
        val id: String,
        val spellCount: Int,
        val priceCombinations: List<List<PriceDto>>
)