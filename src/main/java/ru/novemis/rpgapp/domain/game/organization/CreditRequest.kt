package ru.novemis.rpgapp.domain.game.organization

import ru.novemis.rpgapp.domain.game.Currency
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import java.util.*
import javax.persistence.*

@Entity
data class CreditRequest (
        @Id
        val id: String = UUID.randomUUID().toString(),

        val duration: Int = 0,

        val amount: Int = 0,

        @ManyToOne
        @JoinColumn(name = "game_id")
        val currency: Currency? = null,

        @Column(columnDefinition = "TEXT")
        val purpose: String = "",

        @ManyToOne
        @JoinColumn(name = "organization_id")
        val organization: Organization? = null,

        @ManyToOne
        @JoinColumn(name = "character_id")
        val requester: GameCharacter? = null,

        val status: CreditRequestStatus = CreditRequestStatus.PENDING
)