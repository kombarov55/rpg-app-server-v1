package ru.novemis.rpgapp.dto.game.skill.dto

import ru.novemis.rpgapp.dto.game.common.dto.PriceDto

data class SchoolLvlDto(
        val lvl: Int,
        val upgradePriceCombinations: List<List<PriceDto>>,
        val spells: List<SpellDto>
)