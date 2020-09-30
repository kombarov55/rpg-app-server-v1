package ru.novemis.rpgapp.domain.game.skill

import ru.novemis.rpgapp.domain.game.common.PriceCombination
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class SkillUpgrade(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var lvlNum: Int = -1,

        var description: String = "",

        @OneToMany(cascade = [CascadeType.ALL])
        var prices: List<PriceCombination> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "skill_id")
        var skill: Skill? = null
)