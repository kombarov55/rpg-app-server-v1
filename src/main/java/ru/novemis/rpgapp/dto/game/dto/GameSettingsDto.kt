package ru.novemis.rpgapp.dto.game.dto

data class GameSettingsDto(
        val id: String,
        val disclaimerText: String,
        val isCharImgUploadable: Boolean,
        val maxEquippedAmounts: List<MaxEquippedAmountDto>,
        val inventorySize: Int
)