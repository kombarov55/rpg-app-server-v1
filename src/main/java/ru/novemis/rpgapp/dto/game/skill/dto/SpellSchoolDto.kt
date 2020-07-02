package ru.novemis.rpgapp.dto.game.skill.dto

data class SpellSchoolDto(
        val img: String,
        val name: String,
        val description: String,
        val minSpellCountToUpgrade: Int,
        val schoolLvls: List<SchoolLvlDto>
)