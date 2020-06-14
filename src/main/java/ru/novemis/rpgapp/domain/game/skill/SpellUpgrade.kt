package ru.novemis.rpgapp.domain.game.skill

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class SpellUpgrade(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var level: Int = -1,

        var ownedSpells: Int = -1,

        @OneToMany
        var upgradeOptions: List<UpgradeOption> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "spell_school_id")
        var spellSchool: SpellSchool? = null
)