package ru.novemis.rpgapp.domain.game.shop

import ru.novemis.rpgapp.domain.game.skill.Skill
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

@Entity
class SkillInfluence(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToMany
        @JoinTable(
                name = "skill_influence__skill",
                joinColumns = [JoinColumn(name = "skill_influence_id")],
                inverseJoinColumns = [JoinColumn(name = "skill_id")]
        )
        var skill: Skill? = null,

        var modifier: AriphmericModifier? = null,

        var amount: Int = 0
)