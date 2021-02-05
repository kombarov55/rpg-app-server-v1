package ru.novemis.rpgapp.converter

import org.mapstruct.Mapper
import ru.novemis.rpgapp.domain.game.GameSettings
import ru.novemis.rpgapp.dto.game.dto.GameSettingsDto

@Mapper(uses = [MaxEquippedAmountsConverterV2::class])
interface GameSettingsConverter {
    fun toDto(domain: GameSettings): GameSettingsDto
}