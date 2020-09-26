package ru.novemis.rpgapp.dto.game.organization.dto

import ru.novemis.rpgapp.dto.game.CurrencyDto

data class CreditOfferDto(
        val id: String,
        val name: String,
        val description: String,
        val currency: CurrencyDto,
        val minAmount: Int,
        val maxAmount: Int,
        val rate: Double,
        val maxDurationInDays: Int
)