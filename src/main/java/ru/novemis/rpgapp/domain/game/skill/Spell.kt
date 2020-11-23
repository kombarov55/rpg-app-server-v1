package ru.novemis.rpgapp.domain.game.skill

import java.util.UUID
import javax.persistence.*

@Entity
data class Spell(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        var img: String = "",

        @ManyToOne
        @JoinColumn(name = "school_lvl_id")
        var schoolLvl: SchoolLvl? = null,

        @ManyToMany
        var requiredSpells: List<Spell> = mutableListOf()
)