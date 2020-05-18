package ru.novemis.rpgapp.dto.game.questionnaire_template

import ru.novemis.rpgapp.dto.game.skill.SkillForm

data class QuestionnaireTemplateForm(
        var gameId: String = "",
        var name: String = "",
        var description: String = "",
        var questionnaireTemplateItems: List<QuestionnaireTemplateItemForm> = emptyList(),
        var skillPointsDistribution: List<SkillPointsDistributionForm> = emptyList(),
        var skills: List<SkillForm> = emptyList()
)