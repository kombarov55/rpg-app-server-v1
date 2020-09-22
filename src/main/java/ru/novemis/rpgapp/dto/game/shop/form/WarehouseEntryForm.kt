package ru.novemis.rpgapp.dto.game.shop.form

data class WarehouseEntryForm(
        val id: String? = null,
        val merchandise: MerchandiseForm? = null,
        val amount: Int = 0
)