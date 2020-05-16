package ru.novemis.rpgapp.dto.game.skill

data class UpgradeCostOptionDto(
        var costs: List<UpgradeCostOptionEntryDto> = emptyList()
)