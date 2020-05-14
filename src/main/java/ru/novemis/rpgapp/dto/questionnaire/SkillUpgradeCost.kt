package ru.novemis.rpgapp.dto.questionnaire

data class SkillUpgradeCost(
        var lvlNum: Int = -1,
        var options: List<SkillUpgradeCostOption> = emptyList()
)