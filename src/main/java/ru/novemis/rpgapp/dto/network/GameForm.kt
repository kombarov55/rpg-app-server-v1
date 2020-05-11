package ru.novemis.rpgapp.dto.network

data class GameForm(
        var title: String = "",
        var description: String = "",
        var imgSrc: String = "",
        var currencies: List<String> = emptyList(),
        var skillTypes: List<String> = emptyList()
)