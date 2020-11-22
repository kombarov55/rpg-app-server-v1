package ru.novemis.rpgapp.dto.game.organization.dto

import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto

data class OverdueCreditDto(
        val id: String,
        val owner: GameCharacterShortDto,
        val debtAmount: Int,
        val currencyName: String,
        val overdueDurationInDays: Int
)