package ru.novemis.rpgapp.dto.game.crafting.dto

import ru.novemis.rpgapp.dto.game.shop.dto.ItemTemplateShortDto

class ItemTemplateAmountDto(
        val id: String,
        val itemTemplate: ItemTemplateShortDto,
        val amount: Int
)