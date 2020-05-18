package ru.novemis.rpgapp.dto.game

class ConversionForm(
        var id: String? = null,
        var currency1: CurrencyForm? = null,
        var currency2: CurrencyForm? = null,
        var conversionPrice1to2: Double = .0,
        var conversionPrice2to1: Double = .0
)