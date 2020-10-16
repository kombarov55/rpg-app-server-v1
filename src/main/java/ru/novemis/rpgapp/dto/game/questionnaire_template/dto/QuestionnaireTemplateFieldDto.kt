package ru.novemis.rpgapp.dto.game.questionnaire_template.dto

import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplateFieldType

data class QuestionnaireTemplateFieldDto(
        val id: String,
        val name: String,
        val description: String,
        val type: QuestionnaireTemplateFieldType,
        val choices: List<String>?
)