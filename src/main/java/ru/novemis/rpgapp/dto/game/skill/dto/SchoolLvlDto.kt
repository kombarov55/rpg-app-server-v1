package ru.novemis.rpgapp.dto.game.skill.dto

data class SchoolLvlDto(
        val id: String,
        val lvl: Int,
        val spellPurchaseOptions: List<SpellPurchaseOptionDto>,
        val spells: List<SpellDto>
)