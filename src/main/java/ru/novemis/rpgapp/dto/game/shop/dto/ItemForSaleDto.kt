package ru.novemis.rpgapp.dto.game.shop.dto

import ru.novemis.rpgapp.dto.game.common.dto.PriceDto
import java.util.*

data class ItemForSaleDto(
        val id: String,
        val itemTemplate: ItemShortDto,
        val price: List<PriceDto>,
        val creationDate: Date,
        val ownerBalanceId: String?,
        val ownerName: String?
)