package ru.novemis.rpgapp.dto.game.shop.dto

import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.dto.game.character.dto.SkillStatsDto

class ItemDto(
        val id: String,
        val name: String,
        val img: String,
        val description: String,
        val category: String,
        val type: String,
        val slots: Int,
        val skillInfluences: List<SkillStatsDto>,
        val destination: Destination,
        val canBeEquipped: Boolean,
        val canBeUsedInCraft: Boolean,
        val canBeCrafted: Boolean,
        val upgradable: Boolean,
        val lvl: Int,
        val upgrades: List<ItemUpgradeDto>,
        val templateId: String
)