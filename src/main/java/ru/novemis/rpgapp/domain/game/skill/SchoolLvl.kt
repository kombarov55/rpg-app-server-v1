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
data class SchoolLvl(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var lvl: Int = 0,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
        var upgradePriceCombinations: List<PriceCombination> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "schoolLvl")
        var spells: List<Spell> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "spell_school_id")
        var spellSchool: SpellSchool? = null

)