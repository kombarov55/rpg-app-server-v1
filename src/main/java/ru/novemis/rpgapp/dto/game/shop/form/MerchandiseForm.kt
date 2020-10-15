package ru.novemis.rpgapp.dto.game.shop.form

import ru.novemis.rpgapp.domain.game.shop.Destination

data class MerchandiseForm(
        var id: String? = null,
        var name: String = "",
        var img: String = "",
        val description: String = "",
        var category: MerchandiseCategoryForm? = null,
        var type: MerchandiseTypeForm? = null,
        var slots: Int = 0,
        var skillInfluences: List<SkillInfluenceForm> = mutableListOf(),
        val destination: Destination? = null,
        val upgradable: Boolean = false,
        var upgrades: List<MerchandiseUpgradeForm> = mutableListOf(),
        val lvl: Int = 0,
        val canBeEquipped: Boolean = false,
        val canBeCrafted: Boolean = false,
        val canBeUsedInCraft: Boolean = false
)