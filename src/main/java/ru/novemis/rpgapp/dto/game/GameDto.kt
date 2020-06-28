package ru.novemis.rpgapp.dto.game


data class GameDto(
        var id: String,
        var title: String,
        var description: String,
        var imgSrc: String,
        var backgroundImgSrc: String,
        var groupLink: String,
        var currencies: List<CurrencyDto>
)