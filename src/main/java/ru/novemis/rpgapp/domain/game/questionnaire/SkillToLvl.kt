package ru.novemis.rpgapp.domain.game.questionnaire

import ru.novemis.rpgapp.domain.game.skill.Skill
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class SkillToLvl(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "skill_id")
        var skill: Skill? = null,

        var amount: Int = 0,

        @ManyToOne
        @JoinColumn(name = "questionnaire_id")
        var questionnaire: Questionnaire? = null
)