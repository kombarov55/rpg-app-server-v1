package ru.novemis.rpgapp.dto.game.shop.dto

data class WarehouseEntryDto(
        val id: String,
        val merchandise: MerchandiseDto,
        val amount: Int
)