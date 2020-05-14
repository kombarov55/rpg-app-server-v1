package ru.novemis.rpgapp.domain.game

import ru.novemis.rpgapp.domain.game.Game
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

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
)