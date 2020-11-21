package ru.novemis.rpgapp.domain.game.organization

import ru.novemis.rpgapp.domain.game.Currency
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Credit(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "currency_id")
        val currency: Currency? = null,

        val amount: Int = 0,

        val rate: Double = 0.0,

        val durationInDays: Int = 0,

        val openingDate: Date = Date(),

        @ManyToOne
        @JoinColumn(name = "organization_id")
        val organization: Organization? = null,

        @ManyToOne
        @JoinColumn(name = "character_id")
        val owner: GameCharacter? = null
)