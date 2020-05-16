package ru.novemis.rpgapp.dto.game.skill

data class UpgradeCostDto(
        var lvlNum: Int = -1,
        var options: List<UpgradeCostOptionDto> = emptyList()
)