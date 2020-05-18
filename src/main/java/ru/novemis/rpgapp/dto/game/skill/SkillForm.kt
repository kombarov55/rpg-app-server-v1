package ru.novemis.rpgapp.dto.game.skill

data class SkillForm(
        var id: String = "",
        var gameId: String = "",
        var name: String = "",
        var description: String = "",
        var imgSrc: String = "",
        var type: String = "",
        var currenciesForUpgrade: List<String> = emptyList(),
        var upgradeOptions: List<SkillUpgradeOptionForm> = emptyList(),
        var upgradeCosts: List<UpgradeCostDto> = emptyList()
)