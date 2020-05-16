package ru.novemis.rpgapp.dto.game.skill

data class SkillDto(
        val id: String,
        val name: String,
        val type: String,
        val description: String,
        val imgSrc: String,
        val upgradeCosts: List<UpgradeCostDto>
)