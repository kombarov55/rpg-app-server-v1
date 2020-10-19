package ru.novemis.rpgapp.domain.game.questionnaire

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import java.util.*
import javax.persistence.*

@Entity
class Questionnaire(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "questionnaire", orphanRemoval = true)
        var fieldToValueList: List<FieldToValue> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "questionnaire_template_id")
        var questionnaireTemplate: QuestionnaireTemplate? = null,

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null,

        @ManyToOne
        @JoinColumn(name = "user_acccount_id")
        var userAccount: UserAccount? = null
)