package ru.novemis.rpgapp.dto.game.shop.dto

import ru.novemis.rpgapp.domain.game.shop.ItemCategory

data class ItemCategoryDto(
        val id: String? = null,
        val name: String = ""
) {
    fun toDomain(): ItemCategory {
        return ItemCategory(
                id = id!!,
                name = name
        )
    }
}