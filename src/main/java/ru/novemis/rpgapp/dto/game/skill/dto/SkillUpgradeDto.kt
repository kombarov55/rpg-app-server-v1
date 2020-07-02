package ru.novemis.rpgapp.dto.game.skill.dto

data class SkillUpgradeDto(
        val lvlNum: Int,
        val description: String,
        val prices: List<String>
)