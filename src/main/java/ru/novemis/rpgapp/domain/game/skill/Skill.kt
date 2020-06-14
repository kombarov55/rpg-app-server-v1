package ru.novemis.rpgapp.domain.game.skill

import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class Skill(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        var img: String = "",

        var description: String = "",

        var price: Int = -1,

        var upgradable: Boolean = false,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "skill")
        val skillUpgrades: List<SkillUpgrade> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "skill_category_id")
        var skillCategory: SkillCategory? = null
)