package ru.novemis.rpgapp.dto.useraccount.dto

import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto
import ru.novemis.rpgapp.dto.game.dto.GameShortDto

data class GameToActiveCharacterDto(
        val id: String, 
        val game: GameShortDto, 
        val activeCharacter: GameCharacterShortDto
)