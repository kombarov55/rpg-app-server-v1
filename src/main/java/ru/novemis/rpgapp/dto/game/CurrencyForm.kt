package ru.novemis.rpgapp.dto.game

data class CurrencyForm(
        var id: String? = null,
        var name: String = "",
        var priceInActivityPoints: Int = -1
)