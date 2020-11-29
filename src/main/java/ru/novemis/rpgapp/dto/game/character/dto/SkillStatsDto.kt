package ru.novemis.rpgapp.dto.game.character.dto

data class SkillStatsDto(
        val skillName: String,
        val initialAmount: Int,
        val bonusAmount: Int
)