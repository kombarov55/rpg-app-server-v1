package ru.novemis.rpgapp.dto.game.character.dto

import ru.novemis.rpgapp.dto.game.common.dto.PriceDto

data class GameCharacterDto(
        val id: String,
        val fieldNameToValueList: Map<String, String>,
        val balance: List<PriceDto>
)