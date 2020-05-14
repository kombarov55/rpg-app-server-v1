package ru.novemis.rpgapp.dto.questionnaire

data class SkillForm(
        var name: String = "",
        var description: String = "",
        var type: String = "",
        var currenciesForUpgrade: List<String> = emptyList(),
        var upgradeOptions: List<SkillUpgradeOptionForm> = emptyList(),
        var upgradeCosts: List<SkillUpgradeCost> = emptyList()
)