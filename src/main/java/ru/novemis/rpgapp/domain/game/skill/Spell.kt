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

        var img: String = "",

        var description: String = "",

        var schoolLevel: Int = -1,

        @ManyToOne
        @JoinColumn(name = "spell_school_id")
        var spellSchool: SpellSchool? = null
)