package ru.novemis.rpgapp.dto.game.skill.form

import ru.novemis.rpgapp.dto.game.common.form.PriceForm

data class SpellSchoolForm(
        var name: String = "",
        var img: String = "",
        var description: String = "",
        var minSpellCountToUpgrade: Int = 0,
        var schoolLvls: List<SchoolLvlForm> = mutableListOf(),
        var purchasePriceCombinations: List<List<PriceForm>> = mutableListOf()
)