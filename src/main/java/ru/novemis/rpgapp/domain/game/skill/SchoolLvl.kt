package ru.novemis.rpgapp.domain.game.skill

import java.util.*
import javax.persistence.*

@Entity
data class SchoolLvl(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var lvl: Int = 0,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "schoolLvl")
        var spellPurchaseOptions: List<SpellPurchaseOption> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "schoolLvl")
        var spells: List<Spell> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "spell_school_id")
        var spellSchool: SpellSchool? = null
)