package ru.novemis.rpgapp.dto.game.organization.form

import ru.novemis.rpgapp.dto.game.form.CurrencyForm

data class CreditOfferForm(
        val name: String = "",
        val description: String = "",
        val currency: CurrencyForm? = null,
        val minAmount: Int = 0,
        val maxAmount: Int = 0,
        val rate: Double = 0.0,
        val maxDurationInDays: Int = 0
)