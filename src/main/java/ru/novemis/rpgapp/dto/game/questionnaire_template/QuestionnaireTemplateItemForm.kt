package ru.novemis.rpgapp.dto.game.questionnaire_template

data class QuestionnaireTemplateItemForm(
        var id: String? = null,
        var name: String = "",
        var type: String = "",
        var listValues: List<String>? = mutableListOf()
)