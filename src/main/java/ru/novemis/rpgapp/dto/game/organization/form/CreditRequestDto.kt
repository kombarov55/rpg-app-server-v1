package ru.novemis.rpgapp.dto.game.organization.form

import ru.novemis.rpgapp.domain.game.organization.CreditRequestStatus
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto

data class CreditRequestDto(
        val id: String,
        val duration: Int,
        val amount: Int,
        val currencyName: String,
        val purpose: String,
        val status: CreditRequestStatus,
        val requester: GameCharacterShortDto
)