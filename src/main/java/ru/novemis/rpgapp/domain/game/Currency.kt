package ru.novemis.rpgapp.domain.game

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Currency(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        var priceInActivityPoints: Int = 0,

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
)