package ru.novemis.rpgapp.dto.game.dto

data class CurrencyDto(
        var id: String? = null,
        var name: String = "",
        var priceInActivityPoints: Int = -1
)