package ru.novemis.rpgapp.dto.view

import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterRoleDto
import ru.novemis.rpgapp.dto.game.dto.GameShortDto
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountDto

data class OfficeViewDto(
    val userAccount: UserAccountDto,
    val gamesToCharacters: List<GameToCharacters>
)

data class GameToCharacters(
    val game: GameShortDto,
    val characters: List<GameCharacterRoleDto>
)