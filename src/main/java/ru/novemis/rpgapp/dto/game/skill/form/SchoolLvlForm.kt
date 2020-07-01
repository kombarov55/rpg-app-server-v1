package ru.novemis.rpgapp.dto.game.skill.form

import ru.novemis.rpgapp.dto.game.common.form.PriceForm

data class SchoolLvlForm(
        var spells: List<SpellForm> = mutableListOf(),
        var schoolLvlUpgradePriceCombinations: List<List<PriceForm>> = mutableListOf(),
        var lvl: Int = 0
)