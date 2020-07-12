package ru.novemis.rpgapp.dto.game.shop.form

import ru.novemis.rpgapp.dto.game.common.form.PriceForm

data class MerchandiseForm(
        var name: String = "",
        var img: String = "",
        var categoryId: String = "",
        var typeId: String = "",
        var slots: Int = 0,
        var prices: List<List<PriceForm>> = mutableListOf(),
        var skillInfluences: List<SkillInfluenceForm> = mutableListOf()
)