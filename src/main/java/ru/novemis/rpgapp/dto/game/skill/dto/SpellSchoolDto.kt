package ru.novemis.rpgapp.dto.game.skill.dto

import ru.novemis.rpgapp.dto.game.common.dto.PriceDto

data class SpellSchoolDto(
        val id: String,
        val img: String,
        val name: String,
        val description: String,
        val minSpellCountToUpgrade: Int,
        val schoolLvls: List<SchoolLvlDto>,
        val purchasePriceCombinations: List<List<PriceDto>>
)