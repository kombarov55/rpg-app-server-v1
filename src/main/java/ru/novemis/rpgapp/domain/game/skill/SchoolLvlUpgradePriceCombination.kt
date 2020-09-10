package ru.novemis.rpgapp.domain.game.skill

import ru.novemis.rpgapp.domain.game.common.PriceCombination
import java.util.*
import javax.persistence.*

@Entity
data class SchoolLvlUpgradePriceCombination(
        @Id
        val id: String = UUID.randomUUID().toString(),

        var spellCount: Int = 0,

        @OneToMany(cascade = [CascadeType.ALL])
        var priceCombinations: List<PriceCombination> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "school_lvl_id")
        var schoolLvl: SchoolLvl? = null
)