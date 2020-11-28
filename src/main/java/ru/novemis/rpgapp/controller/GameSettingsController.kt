package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.GameSettingsConverter
import ru.novemis.rpgapp.dto.game.dto.GameSettingsDto
import ru.novemis.rpgapp.dto.game.dto.MaxEquippedAmountDto
import ru.novemis.rpgapp.repository.game.GameSettingsRepository
import ru.novemis.rpgapp.repository.game.MaxEquippedAmountRepository
import javax.transaction.Transactional

@RestController
open class GameSettingsController(
        private val repository: GameSettingsRepository,
        private val converter: GameSettingsConverter,
        private val maxEquipmentAmountRepository: MaxEquippedAmountRepository
) {

    data class GameSettingsPatch(
            val disclaimerText: String? = null,
            val charImgUploadable: Boolean? = null,
            val inventorySize: Int? = null,
            val maxEquippedAmounts: List<MaxEquippedAmountDto>? = null
    )

    @Transactional
    @PatchMapping("/game/{id}/settings")
    open fun patch(
            @PathVariable("id") gameId: String,
            @RequestBody patch: GameSettingsPatch
    ): GameSettingsDto {
        return repository.findByGameId(gameId)
                .applyPatch(patch, maxEquipmentAmountRepository)
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @Transactional
    @GetMapping("/game/{id}/settings")
    open fun getByGameId(
            @PathVariable("id") gameId: String
    ): GameSettingsDto {
        return repository.findByGameId(gameId).let { converter.toDto(it) }

    }
}