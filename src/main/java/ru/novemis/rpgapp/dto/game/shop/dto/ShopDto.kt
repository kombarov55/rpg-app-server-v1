package ru.novemis.rpgapp.dto.game.shop.dto

import ru.novemis.rpgapp.domain.game.shop.ShopType

data class ShopDto(
        val id: String,
        val name: String,
        val img: String,
        val type: ShopType,
        val merchandiseCategories: List<MerchandiseCategoryDto>,
        val merchandiseTypes: List<MerchandiseTypeDto>
)