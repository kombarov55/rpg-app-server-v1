package ru.novemis.rpgapp.dto.game.questionnaire.form

data class QuestionnaireForm(
        var fieldToValueList: List<FieldToValueForm> = mutableListOf()
)