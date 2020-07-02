package ru.novemis.rpgapp.dto.game.skill.dto

data class SchoolLvlDto(
        val img: String,
        val name: String,
        val description: String,
        val spells: List<SpellDto>,
        val purchasePriceCombinations: List<String>
)