package ru.novemis.rpgapp.domain.game.organization

import ru.novemis.rpgapp.domain.game.Currency
import java.util.*
import javax.persistence.*

@Entity
class CreditOffer(

        @Id
        var id: String = UUID.randomUUID().toString(),

        val name: String = "",

        @Column(columnDefinition = "TEXT")
        val description: String = "",

        @ManyToOne
        @JoinColumn(name = "currency_id")
        val currency: Currency? = null,

        val minAmount: Int = 0,

        val maxAmount: Int = 0,

        val rate: Double = 0.0,

        val minDurationInDays: Int = 0,

        val maxDurationInDays: Int = 0,

        @ManyToOne
        @JoinColumn(name = "organization_id")
        var organization: Organization? = null
)