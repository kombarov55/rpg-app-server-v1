package ru.novemis.rpgapp.dto.game.crafting.form

import ru.novemis.rpgapp.dto.game.shop.form.ItemTemplateShortForm

class ItemTemplateAmountForm(
        var itemTemplate: ItemTemplateShortForm? = null,
        var amount: Int = 0
)