package ru.novemis.rpgapp.dto.questionnaire

data class SkillUpgradeCostOption(
        var costs: List<SkillUpgradeCostOptionCost> = emptyList()
)