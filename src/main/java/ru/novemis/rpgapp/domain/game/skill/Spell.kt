package ru.novemis.rpgapp.domain.game.skill

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Spell(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        var description: String = "",

        var img: String = "",

        @ManyToOne
        @JoinColumn(name = "school_lvl_id")
        var schoolLvl: SchoolLvl? = null
)