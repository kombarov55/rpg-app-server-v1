package ru.novemis.rpgapp.dto.game.character.dto

import ru.novemis.rpgapp.domain.game.character.GameCharacterStatus
import ru.novemis.rpgapp.dto.game.common.dto.PriceDto
import ru.novemis.rpgapp.dto.game.dto.GameShortDto
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationShortDto

data class GameCharacterShortDto(
        val id: String,
        val name: String,
        val game: GameShortDto? = null,
        val country: OrganizationShortDto? = null,
        val status: GameCharacterStatus? = null,
        val statusChangeDate: Long? = null,
        val balanceId: String? = null,
        val balance: List<PriceDto>? = null
)