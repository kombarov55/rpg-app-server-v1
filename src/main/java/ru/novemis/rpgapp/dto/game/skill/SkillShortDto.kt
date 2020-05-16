package ru.novemis.rpgapp.dto.game.skill

data class SkillShortDto(
        val id: String,
        val name: String,
        val description: String,
        val imgSrc: String,
        val upgradeCosts: List<UpgradeCostDto>
)