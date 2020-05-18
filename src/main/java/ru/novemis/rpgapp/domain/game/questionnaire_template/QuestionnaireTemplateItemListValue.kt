package ru.novemis.rpgapp.domain.game.questionnaire_template

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class QuestionnaireTemplateItemListValue(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "questionnaire_item_id")
        var questionnaireTemplateItem: QuestionnaireTemplateItem? = null,

        var value: String = ""
)