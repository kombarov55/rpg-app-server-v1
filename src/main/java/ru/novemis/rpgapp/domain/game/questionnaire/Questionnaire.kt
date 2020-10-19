package ru.novemis.rpgapp.domain.game.questionnaire

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.organization.Organization
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate
import ru.novemis.rpgapp.domain.game.skill.Spell
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import java.util.*
import javax.persistence.*

@Entity
class Questionnaire(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "questionnaire", orphanRemoval = true)
        var fieldToValueList: List<FieldToValue> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "questionnaire", orphanRemoval = true)
        var selectedSkillToLvlList: List<SkillToLvl> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "country_id")
        var country: Organization? = null,

        @ManyToMany
        @JoinTable(
                name = "questionnaire__spell",
                joinColumns = [JoinColumn(name = "questionnaire_id")],
                inverseJoinColumns = [JoinColumn(name = "spell_id")]
        )
        var selectedSpells: List<Spell> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "questionnaire_template_id")
        var questionnaireTemplate: QuestionnaireTemplate? = null,

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null,

        @ManyToOne
        @JoinColumn(name = "user_acccount_id")
        var author: UserAccount? = null,

        val creationDate: Date = Date(),

        var status: QuestionnaireStatus = QuestionnaireStatus.ON_REVIEW,

        var statusChangeDate: Date = Date()
)