package ru.novemis.rpgapp.dto.game.shop.dto

import ru.novemis.rpgapp.domain.game.shop.Destination

data class ItemTemplateDto(
        val id: String,
        val name: String,
        val img: String,
        val description: String,
        val category: ItemCategoryDto,
        val type: ItemTypeDto,
        val slots: Int,
        val skillInfluences: List<SkillInfluenceDto>,
        val destination: Destination,
        val canBeEquipped: Boolean,
        val canBeUsedInCraft: Boolean,
        val canBeCrafted: Boolean,
        val upgradable: Boolean,
        val upgrades: List<ItemUpgradeDto>
)