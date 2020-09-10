package ru.novemis.rpgapp.dto.game.skill.form

import ru.novemis.rpgapp.dto.game.common.form.PriceForm

data class SchoolLvlUpgradePriceCombinationForm(
        val spellCount: Int = 0,
        val priceCombinationList: List<List<PriceForm>> = mutableListOf()
)