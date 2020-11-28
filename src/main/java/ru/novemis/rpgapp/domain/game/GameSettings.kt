package ru.novemis.rpgapp.domain.game

import ru.novemis.rpgapp.controller.GameSettingsController
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

        var inventorySize: Int = 0
) {
        fun applyPatch(patch: GameSettingsController.GameSettingsPatch): GameSettings {
                return apply {
                        disclaimerText = patch.disclaimerText ?: disclaimerText
                        isCharImgUploadable = patch.charImgUploadable ?: isCharImgUploadable
                        maxEquippedAmounts = patch.maxEquippedAmounts?.map { it.toDomain() } ?: maxEquippedAmounts
                        inventorySize = patch.inventorySize ?: inventorySize
                }
        }
}