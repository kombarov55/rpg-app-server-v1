package ru.novemis.rpgapp.domain.game

import ru.novemis.rpgapp.controller.GameSettingsController
import ru.novemis.rpgapp.repository.game.MaxEquippedAmountRepository
import java.util.*
import javax.persistence.*

@Entity
class GameSettings(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @OneToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null,

        @Column(columnDefinition = "TEXT")
        var disclaimerText: String = "",

        var isCharImgUploadable: Boolean = false,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "gameSettings")
        var maxEquippedAmounts: List<MaxEquippedAmount> = emptyList(),

        var inventorySize: Int = 0,

        var maxOwnedPetsCount: Int = 0
) {
    fun applyPatch(patch: GameSettingsController.GameSettingsPatch, maxEquippedAmountRepository: MaxEquippedAmountRepository): GameSettings = apply {
        val settings = this

        disclaimerText = patch.disclaimerText ?: disclaimerText
        isCharImgUploadable = patch.charImgUploadable ?: isCharImgUploadable
        maxEquippedAmounts = patch.maxEquippedAmounts?.map { it.toDomain(gameSettings = this) }
                ?.also { settings.maxEquippedAmounts.forEach { maxEquippedAmountRepository.delete(it) } }
                ?: maxEquippedAmounts
        inventorySize = patch.inventorySize ?: inventorySize
        maxOwnedPetsCount = patch.maxOwnedPetsCount ?: maxOwnedPetsCount
    }
}