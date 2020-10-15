package ru.novemis.rpgapp.dto.game.shop.dto

import ru.novemis.rpgapp.domain.game.shop.Destination

data class MerchandiseDto(
        val id: String,
        val name: String,
        val img: String,
        val description: String,
        val category: MerchandiseCategoryDto,
        val type: MerchandiseTypeDto,
        val slots: Int,
        val skillInfluences: List<SkillInfluenceDto>,
        val destination: Destination,
        val canBeEquipped: Boolean,
        val canBeUsedInCraft: Boolean,
        val canBeCrafted: Boolean,
        val upgrades: List<MerchandiseUpgradeDto>
)