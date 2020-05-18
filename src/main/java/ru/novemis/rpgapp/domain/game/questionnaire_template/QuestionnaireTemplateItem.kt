package ru.novemis.rpgapp.domain.game.questionnaire_template

import ru.novemis.rpgapp.domain.game.questionnaire_template.enum.QuestionnaireItemType
import java.util.*
import javax.persistence.*

@Entity
data class QuestionnaireTemplateItem(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        @Enumerated(EnumType.STRING)
        var type: QuestionnaireItemType = QuestionnaireItemType.NONE,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "questionnaireTemplateItem")
        var listValues: List<QuestionnaireTemplateItemListValue> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "questionnaire_id")
        var questionnaireTemplate: QuestionnaireTemplate? = null

)