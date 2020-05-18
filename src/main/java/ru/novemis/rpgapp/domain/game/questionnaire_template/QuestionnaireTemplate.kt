package ru.novemis.rpgapp.domain.game.questionnaire_template

import ru.novemis.rpgapp.domain.game.Game
import java.util.*
import javax.persistence.*

@Entity
data class QuestionnaireTemplate(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        var imgSrc: String = "",

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "questionnaireTemplate")
        var templateItems: List<QuestionnaireTemplateItem> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "questionnaireTemplate")
        var skillPointsDistributions: List<SkillPointsDistribution> = mutableListOf(),

//        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "questionnaire")
//        var skills: List<Skill> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null,

        var deleted: Boolean = false
)