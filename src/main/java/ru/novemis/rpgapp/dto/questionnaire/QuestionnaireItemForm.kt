package ru.novemis.rpgapp.dto.questionnaire

data class QuestionnaireItemForm(
        var name: String = "",
        var type: String = "",
        var listValues: List<String>? = mutableListOf()
)