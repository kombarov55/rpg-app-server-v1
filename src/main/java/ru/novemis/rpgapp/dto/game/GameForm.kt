package ru.novemis.rpgapp.dto.game

data class GameForm(
        var title: String = "",
        var description: String = "",
        var imgSrc: String = "",
        var currencies: List<String> = emptyList(),
        var skillTypes: List<String> = emptyList()
)