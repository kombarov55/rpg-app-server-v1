package ru.novemis.rpgapp.dto.game.questionnaire_template

data class SkillPointsDistributionForm(
        var id: String? = null,
        var skillType: String = "",
        var maxValue: Int = -1
)