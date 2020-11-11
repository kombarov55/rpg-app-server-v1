package ru.novemis.rpgapp.dto.game.shop.form

import ru.novemis.rpgapp.dto.game.common.form.PriceForm

data class ItemUpgradeForm(
        var id: String? = null,
        val lvlNum: Int = 0,
        val skillInfluences: List<SkillInfluenceForm> = mutableListOf(),
        val prices: List<List<PriceForm>> = mutableListOf()
)