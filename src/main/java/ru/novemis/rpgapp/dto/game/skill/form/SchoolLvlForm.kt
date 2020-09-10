package ru.novemis.rpgapp.dto.game.skill.form

data class SchoolLvlForm(
        var spells: List<SpellForm> = mutableListOf(),
        var schoolLvlUpgradePriceCombinations: List<SchoolLvlUpgradePriceCombinationForm> = mutableListOf(),
        var lvl: Int = 0
)