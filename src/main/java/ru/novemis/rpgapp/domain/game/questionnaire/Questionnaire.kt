package ru.novemis.rpgapp.domain.game.questionnaire

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.skill.Skill
import java.util.*
import javax.persistence.*

@Entity
data class Questionnaire(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "questionnaire")
        var items: List<QuestionnaireItem> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "questionnaire")
        var skillPointsDistributions: List<SkillPointsDistribution> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "questionnaire")
        var skills: List<Skill> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
)