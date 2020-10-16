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

        var name: String = "",

        var description: String? = "",

        var type: QuestionnaireTemplateFieldType = QuestionnaireTemplateFieldType.STRING,

        var choicesDelimitedByComma: String? = null,

        @ManyToOne
        @JoinColumn(name = "questionnaire_template_id")
        var questionnaireTemplate: QuestionnaireTemplate? = null
)