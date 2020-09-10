package ru.novemis.rpgapp.dto.game.skill.dto

data class SchoolLvlDto(
        val lvl: Int,
        val upgradePriceCombinations: List<SchoolLvlUpgradePriceCombinationDto>,
        val spells: List<SpellDto>
)