package ru.novemis.rpgapp.domain.game.questionnaire_template

import java.util.*
import javax.persistence.*

@Entity
class QuestionnaireTemplateField (
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String? = "",

        var type: QuestionnaireTemplateFieldType = QuestionnaireTemplateFieldType.STRING,

        var choicesDelimitedByComma: String? = null,

        @ManyToOne
        @JoinColumn(name = "questionnaire_template_id")
        var questionnaireTemplate: QuestionnaireTemplate? = null
)