package ru.novemis.rpgapp.dto.game.questionnaire_template.form

import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplateFieldType

data class QuestionnaireTemplateFieldForm(
        var id: String? = null,
        var name: String = "",
        var description: String = "",
        var type: QuestionnaireTemplateFieldType = QuestionnaireTemplateFieldType.STRING,
        var choices: List<String>? = mutableListOf()
)