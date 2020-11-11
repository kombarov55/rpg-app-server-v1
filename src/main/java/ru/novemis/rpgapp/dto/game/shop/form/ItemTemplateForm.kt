package ru.novemis.rpgapp.dto.game.shop.form

import ru.novemis.rpgapp.domain.game.shop.Destination

data class ItemTemplateForm(
        var id: String? = null,
        var name: String = "",
        var img: String = "",
        val description: String = "",
        var category: ItemCategoryForm? = null,
        var type: ItemTypeForm? = null,
        var slots: Int = 0,
        var skillInfluences: List<SkillInfluenceForm> = mutableListOf(),
        val destination: Destination? = null,
        val upgradable: Boolean = false,
        var upgrades: List<ItemUpgradeForm> = mutableListOf(),
        val canBeEquipped: Boolean = false,
        val canBeCrafted: Boolean = false,
        val canBeUsedInCraft: Boolean = false
)