package ru.novemis.rpgapp.domain.game.questionnaire_template

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class SkillPointsDistribution(

        @Id
        var id: String = UUID.randomUUID().toString(),

//        @OneToOne
//        @JoinColumn(name = "skill_type_id")
//        var skillType: SkillType? = null,

        var maxValue: Int = -1,

        @ManyToOne
        @JoinColumn(name = "questionnaire_id")
        var questionnaireTemplate: QuestionnaireTemplate? = null
)