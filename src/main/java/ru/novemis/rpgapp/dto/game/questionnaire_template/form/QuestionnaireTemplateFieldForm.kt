package ru.novemis.rpgapp.dto.game.questionnaire_template.form

import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplateFieldType

data class QuestionnaireTemplateFieldForm(
        var name: String = "",
        var description: String = "",
        var img: String = "",
        var type: QuestionnaireTemplateFieldType = QuestionnaireTemplateFieldType.STRING
)