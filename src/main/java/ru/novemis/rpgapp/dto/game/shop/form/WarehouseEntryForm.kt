package ru.novemis.rpgapp.dto.game.shop.form

data class WarehouseEntryForm(
        val id: String? = null,
        val itemTemplate: ItemTemplateForm? = null,
        val amount: Int = 0
)