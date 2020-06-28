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
open class SkillCategory(
        @Id
        var id: String = UUID.randomUUID().toString(),
        var name: String = "",
        var description: String = "",
        var img: String = "",
        var complex: Boolean = false
)

@Entity
class SimpleSkillCategory(
        id: String = UUID.randomUUID().toString(),
        name: String = "",
        description: String = "",
        img: String = "",

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "skillCategory")
        var skills: List<Skill> = mutableListOf()
) : SkillCategory(id, name, description, img, false)

@Entity
class ComplexSkillCategory(
        id: String = UUID.randomUUID().toString(),
        name: String = "",
        description: String = "",
        img: String = "",

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "skillCategory")
        var spellSchools: List<SpellSchool> = mutableListOf()
) : SkillCategory(id, name, description, img, true)