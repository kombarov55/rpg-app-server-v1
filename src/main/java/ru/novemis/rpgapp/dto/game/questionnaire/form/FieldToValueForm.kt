package ru.novemis.rpgapp.dto.game.questionnaire.form

import ru.novemis.rpgapp.dto.game.questionnaire_template.form.QuestionnaireTemplateFieldForm

data class FieldToValueForm(
        var field: QuestionnaireTemplateFieldForm? = null,
        var value: String
)