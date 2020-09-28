package ru.novemis.rpgapp.dto.game.shop.form

import ru.novemis.rpgapp.dto.game.common.form.PriceForm

data class ItemForSaleForm(
        var merchandise: MerchandiseForm? = null,
        var price: List<PriceForm> = mutableListOf(),
        var amount: Int = 0
)