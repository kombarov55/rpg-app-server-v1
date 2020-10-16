package ru.novemis.rpgapp.dto.game.dto

data class ConversionDto(
        val id: String,
        val currency1: String,
        val currency2: String,
        val conversionPrice1to2: Double,
        var conversionPrice2to1: Double
)