package ru.novemis.rpgapp.dto.game.form

data class GameForm(
        var title: String = "",
        var description: String = "",
        var img: String = "",
        var background: String = "",
        var groupLink: String = "",
        var currencies: List<CurrencyForm> = mutableListOf()
)