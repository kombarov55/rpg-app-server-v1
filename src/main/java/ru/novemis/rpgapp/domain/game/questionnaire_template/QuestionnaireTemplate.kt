package ru.novemis.rpgapp.domain.game.questionnaire_template

import ru.novemis.rpgapp.domain.game.Game
import java.util.*
import javax.persistence.*

@Entity
class QuestionnaireTemplate (
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        var description: String = "",

        var img: String = "",

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "questionnaireTemplate", orphanRemoval = true)
        var fields: List<QuestionnaireTemplateField> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "questionnaireTemplate", orphanRemoval = true)
        var skillCategoryToPoints: List<SkillCategoryToPoints> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
)