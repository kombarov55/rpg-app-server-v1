package ru.novemis.rpgapp.domain.game.questionnaire_template

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class QuestionnaireTemplateField (
        @Id
        var id: String = UUID.randomUUID().toString(),

        val name: String = "",

        val description: String = "",

        val type: QuestionnaireTemplateFieldType = QuestionnaireTemplateFieldType.STRING,

        @ManyToOne
        @JoinColumn(name = "questionnaire_template_id")
        var questionnaireTemplate: QuestionnaireTemplate? = null
)