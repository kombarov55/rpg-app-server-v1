package ru.novemis.rpgapp.domain.game.questionnaire

import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplateField
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class FieldToValue (
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "questionnaire_template_field_id")
        var field: QuestionnaireTemplateField? = null,

        var value: String = "",

        @ManyToOne
        @JoinColumn(name = "questionnaire_id")
        var questionnaire: Questionnaire? = null
)