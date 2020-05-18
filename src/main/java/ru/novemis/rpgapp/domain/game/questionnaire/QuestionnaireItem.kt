package ru.novemis.rpgapp.domain.game.questionnaire

import ru.novemis.rpgapp.domain.game.questionnaire.enum.QuestionnaireItemType
import java.util.*
import javax.persistence.*

@Entity
data class QuestionnaireItem(

    @Id
    var id: String = UUID.randomUUID().toString(),

    var name: String = "",

    @Enumerated(EnumType.STRING)
    var type: QuestionnaireItemType = QuestionnaireItemType.NONE,

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "questionnaireItem")
    var listValues: List<QuestionnaireItemListValue> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    var questionnaire: Questionnaire? = null

)