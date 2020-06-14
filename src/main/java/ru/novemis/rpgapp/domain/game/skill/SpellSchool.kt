package ru.novemis.rpgapp.domain.game.skill

import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class SpellSchool(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        var img: String = "",

        var description: String = "",

        var maxLevel: Int = -1,

        var price: Int = -1,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "spellSchool")
        var spells: List<Spell> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "spellSchool")
        var upgrades: List<SpellUpgrade> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "skill_category_id")
        var skillCategory: SkillCategory? = null
)