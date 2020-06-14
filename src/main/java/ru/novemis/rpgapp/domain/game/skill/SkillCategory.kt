package ru.novemis.rpgapp.domain.game.skill

import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class SkillCategory(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        var imgSrc: String = "",

        var description: String = "",

        var complex: Boolean = false,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "skillCategory")
        var skills: List<Skill> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "skillCategory")
        var spellSchool: List<SpellSchool> = mutableListOf()
)