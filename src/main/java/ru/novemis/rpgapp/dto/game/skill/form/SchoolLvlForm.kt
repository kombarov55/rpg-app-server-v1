package ru.novemis.rpgapp.dto.game.skill.form

data class SchoolLvlForm(
        var spells: List<SpellForm> = mutableListOf(),
        var spellPurchaseOptions: List<SpellPurchaseOptionForm> = mutableListOf(),
        var lvl: Int = 0
)