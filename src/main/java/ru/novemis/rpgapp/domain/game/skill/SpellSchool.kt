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
data class SpellSchool(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        var description: String = "",

        var img: String = "",

        var minSpellCountToUpgrade: Int = 0,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
        var purchasePriceCombinations: List<PriceCombination> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "spellSchool")
        var schoolLvls: List<SchoolLvl> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "skill_category_id")
        var skillCategory: SkillCategory? = null
)