package ru.novemis.rpgapp.domain.game

import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate
import ru.novemis.rpgapp.domain.game.skill.Skill
import ru.novemis.rpgapp.domain.game.skill.SkillType
import ru.novemis.rpgapp.domain.network.Network
import ru.novemis.rpgapp.domain.network.Subnetwork
import java.util.*
import javax.persistence.*

@Entity
data class Game(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var title: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        var imgName: String = "",

        var backgroundName: String = "",

        @ManyToOne
        @JoinColumn(name = "network_id")
        var network: Network? = null,

        @ManyToOne
        @JoinColumn(name = "subnetwork_id")
        var subnetwork: Subnetwork? = null,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "game")
        var currencies: List<Currency> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "game")
        var conversions: List<Conversion> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "game")
        var skillTypes: List<SkillType> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "game")
        var questionnaireTemplates: List<QuestionnaireTemplate> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "game")
        var skills: List<Skill> = mutableListOf(),

        var deleted: Boolean = false,

        var deletionDate: Date? = null
)