package ru.novemis.rpgapp.dto.game.skill.dto

data class SkillDto(
        val img: String,
        val name: String,
        val description: String,
        val upgradeOptions: List<String>
)