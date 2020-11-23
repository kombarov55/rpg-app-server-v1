package ru.novemis.rpgapp.domain.useraccount

import ru.novemis.rpgapp.domain.game.Game
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class ActivityPoints(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null,

        var amount: Int = 0,

        @ManyToOne
        @JoinColumn(name = "user_account_id")
        var userAccount: UserAccount? = null
)