package ru.novemis.rpgapp.domain.game.shop

import ru.novemis.rpgapp.domain.game.skill.Skill
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne

@Entity
class SkillInfluence(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "skill_id")
        var skill: Skill? = null,

        var modifier: AriphmericModifier? = null,

        var amount: Int = 0
)