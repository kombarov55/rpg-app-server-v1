package ru.novemis.rpgapp.dto.game.organization.dto

data class CreditDto(
        val id: String,
        val currencyName: String,
        val amount: Int,
        val debtAmount: Int,
        val payedAmount: Int,
        val openingDate: Long,
        val endingDate: Long,
        val durationInDays: Int,
        val remainingDays: Int,
        val lastPaymentDate: Long?,
        val isPaid: Boolean,
        val isOverdue: Boolean,
        val organizationName: String
)