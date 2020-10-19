package ru.novemis.rpgapp.domain.game.shop

import ru.novemis.rpgapp.domain.game.character.GameCharacter
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class MerchandiseToLvl(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "merchandise_id")
        var merchandise: Merchandise? = null,

        var lvl: Int = 0,

        @ManyToOne
        @JoinColumn(name = "game_character_id")
        var character: GameCharacter? = null
)