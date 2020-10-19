package ru.novemis.rpgapp.domain.game.common

import ru.novemis.rpgapp.domain.game.Currency
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Price(

        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "currency_id")
        var currency: Currency? = null,

        val amount: Int = 0,

        @ManyToOne
        @JoinColumn(name = "game_character_id")
        var character: GameCharacter? = null
)