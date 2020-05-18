package ru.novemis.rpgapp.dto.game

class ConversionForm(
        var id: String? = null,
        var currency1: CurrencyForm,
        var currency2: CurrencyForm,
        var conversionPrice1to2: Double,
        var conversionPrice2to1: Double
)