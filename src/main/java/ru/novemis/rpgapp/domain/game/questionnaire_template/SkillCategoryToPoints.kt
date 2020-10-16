package ru.novemis.rpgapp.domain.game.questionnaire_template

import ru.novemis.rpgapp.domain.game.skill.SkillCategory
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class SkillCategoryToPoints(
        @Id
        var id: String = UUID.randomUUID().toString(),
        
        @ManyToOne
        @JoinColumn(name = "skill_category_id")
        var skillCategory: SkillCategory? = null,
        
        val amount: Int = 0,
        
        @ManyToOne
        @JoinColumn(name = "questionnaire_template_id")
        val questionnaireTemplate: QuestionnaireTemplate? = null
)