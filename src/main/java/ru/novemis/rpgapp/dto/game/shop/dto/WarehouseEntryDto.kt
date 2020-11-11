package ru.novemis.rpgapp.dto.game.shop.dto

data class WarehouseEntryDto(
        val id: String,
        val itemTemplate: ItemTemplateDto,
        val amount: Int
)