package ru.novemis.rpgapp.dto.game.skill.form

import ru.novemis.rpgapp.dto.game.common.form.PriceForm

data class SkillUpgradeForm(
        var lvlNum: Int = 0,
        var description: String? = "",
        var prices: List<List<PriceForm>> = mutableListOf()
)