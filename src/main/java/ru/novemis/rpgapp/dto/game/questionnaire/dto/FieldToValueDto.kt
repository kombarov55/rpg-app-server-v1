package ru.novemis.rpgapp.dto.game.questionnaire.dto

import ru.novemis.rpgapp.dto.game.questionnaire_template.dto.QuestionnaireTemplateFieldDto

data class FieldToValueDto (
        val id: String,
        val field: QuestionnaireTemplateFieldDto,
        val value: String
)