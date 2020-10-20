package ru.novemis.rpgapp.dto.game.character.dto

import ru.novemis.rpgapp.dto.game.dto.GameShortDto
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationShortDto

data class GameCharacterShortDto(
        val id: String,
        val name: String,
        val game: GameShortDto,
        val country: OrganizationShortDto
)