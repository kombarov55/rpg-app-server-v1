package ru.novemis.rpgapp.domain.useraccount

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import java.util.*
import javax.persistence.*

@Entity
class GameToActiveCharacter (
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name= "game_id")
        var game: Game? = null,

        @OneToOne
        @JoinColumn(name = "character_id")
        var character: GameCharacter? = null,

        @ManyToOne
        @JoinColumn(name = "user_account_id")
        var userAccount: UserAccount? = null
)