package ru.novemis.rpgapp.dto.questionnaire

data class QuestionnaireForm(
        var name: String,
        var description: String,
        var questionnaireItems: List<QuestionnaireItemForm> = emptyList(),
        var skillPointsDistribution: List<SkillPointsDistributionForm> = emptyList(),
        var skills: List<SkillForm> = emptyList()
)