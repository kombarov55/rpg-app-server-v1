package ru.novemis.rpgapp.domain.useraccount

import ru.novemis.rpgapp.domain.game.Game
import java.util.*
import javax.persistence.*

@Entity
data class UserAccountGameRole (
    @Id
    val id: String = UUID.randomUUID().toString(),

    @ManyToOne
    @JoinColumn
    val userAccount: UserAccount,

    @ManyToOne
    @JoinColumn
    val game: Game,

    var role: Role
)