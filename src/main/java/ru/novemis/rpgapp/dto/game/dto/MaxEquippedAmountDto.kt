package ru.novemis.rpgapp.dto.game.dto

import ru.novemis.rpgapp.domain.game.MaxEquippedAmount
import ru.novemis.rpgapp.dto.game.shop.dto.ItemCategoryDto

data class MaxEquippedAmountDto (
        val id: String = "",
        val itemCategory: ItemCategoryDto? = null,
        val amount: Int = 0
) {
    fun toDomain(): MaxEquippedAmount {
        return MaxEquippedAmount(
                itemCategory = itemCategory!!.toDomain(),
                amount = amount
        )
    }
}