package ru.novemis.rpgapp.domain.game.questionnaire

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class QuestionnaireItemListValue(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "questionnaire_item_id")
        var questionnaireItem: QuestionnaireItem? = null,

        var value: String = ""
)