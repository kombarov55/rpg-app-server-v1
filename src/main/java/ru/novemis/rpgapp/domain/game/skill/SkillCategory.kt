package ru.novemis.rpgapp.domain.game.skill

import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.OneToMany

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
data class SkillCategory(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        var description: String = "",

        var img: String = "",

        var complex: Boolean = false,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "skillCategory")
        var skills: List<Skill> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "skillCategory")
        var spellSchools: List<SpellSchool> = mutableListOf()
)