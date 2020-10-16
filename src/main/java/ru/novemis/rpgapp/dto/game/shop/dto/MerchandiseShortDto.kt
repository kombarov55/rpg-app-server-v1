package ru.novemis.rpgapp.dto.game.shop.dto

data class MerchandiseShortDto(
        val id: String,
        val name: String,
        val img: String,
        val description: String?,
        val canBeEquipped: Boolean,
        val canBeUsedInCraft: Boolean,
        val canBeCrafted: Boolean
)