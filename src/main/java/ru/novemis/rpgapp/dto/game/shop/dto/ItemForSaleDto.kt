package ru.novemis.rpgapp.dto.game.shop.dto

import ru.novemis.rpgapp.dto.game.common.dto.PriceDto

data class ItemForSaleDto(
        val id: String,
        val merchandise: MerchandiseDto,
        val amount: Int,
        val price: List<PriceDto>
)