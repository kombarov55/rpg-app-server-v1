package ru.novemis.rpgapp.dto.game.skill

import ru.novemis.rpgapp.dto.game.skill.UpgradeCostDto
import ru.novemis.rpgapp.dto.game.skill.SkillUpgradeOptionForm

data class SkillForm(
        val gameId: String = "",
        var name: String = "",
        var description: String = "",
        var type: String = "",
        var currenciesForUpgrade: List<String> = emptyList(),
        var upgradeOptions: List<SkillUpgradeOptionForm> = emptyList(),
        var upgradeCosts: List<UpgradeCostDto> = emptyList()
)