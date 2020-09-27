package ru.novemis.rpgapp.dto.game.shop.form

import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.dto.game.common.form.PriceForm

data class MerchandiseForm(
        var id: String? = null,
        var name: String = "",
        var img: String = "",
        val description: String = "",
        var category: MerchandiseCategoryForm? = null,
        var type: MerchandiseTypeForm? = null,
        var slots: Int = 0,
        var prices: List<List<PriceForm>> = mutableListOf(),
        var skillInfluences: List<SkillInfluenceForm> = mutableListOf(),
        val destination: Destination? = null,
        var merchandiseUpgrades: List<MerchandiseUpgradeForm> = mutableListOf()
)