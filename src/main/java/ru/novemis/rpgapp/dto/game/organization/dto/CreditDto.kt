package ru.novemis.rpgapp.dto.game.organization.dto

data class CreditDto(
        val id: String,
        val currencyName: String,
        val amount: Int,
        val payedAmount: Int,
        val openingDate: Long,
        val endingDate: Long,
        val durationInDays: Int,
        val remainingDays: Int,
        val lastPaymentDate: Long?,
        val isOverdue: Boolean,
        val minimalPayment: Int,
        val organizationName: String

)