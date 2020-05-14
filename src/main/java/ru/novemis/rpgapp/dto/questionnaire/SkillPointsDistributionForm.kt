package ru.novemis.rpgapp.dto.questionnaire

data class SkillPointsDistributionForm(
        var skillType: String = "",
        var maxValue: Int = -1
)