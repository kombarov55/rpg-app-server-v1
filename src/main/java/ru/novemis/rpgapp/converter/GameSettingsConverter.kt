package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.GameSettings
import ru.novemis.rpgapp.dto.game.dto.GameSettingsDto

@Component
class GameSettingsConverter(
        private val maxEquippedAmountsConverter: MaxEquippedAmountsConverter
) {

    fun toDto(domain: GameSettings): GameSettingsDto {
        return GameSettingsDto(
                id = domain.id,
                disclaimerText = domain.disclaimerText,
                isCharImgUploadable = domain.isCharImgUploadable,
                maxEquippedAmounts = domain.maxEquippedAmounts.map { maxEquippedAmountsConverter.toDto(it) },
                inventorySize = domain.inventorySize
        )
    }

}