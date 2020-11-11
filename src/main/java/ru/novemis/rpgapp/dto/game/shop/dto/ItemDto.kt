package ru.novemis.rpgapp.dto.game.shop.dto

import ru.novemis.rpgapp.domain.game.shop.Destination

class ItemDto(
        val id: String,
        val name: String,
        val img: String,
        val description: String,
        val category: String,
        val type: String,
        val slots: Int,
        val skillInfluences: List<SkillInfluenceDto>,
        val destination: Destination,
        val canBeEquipped: Boolean,
        val canBeUsedInCraft: Boolean,
        val canBeCrafted: Boolean,
        val upgradable: Boolean,
        val lvl: Int,
        val upgrades: List<ItemUpgradeDto>
)