package ru.novemis.rpgapp.domain.game.common

import ru.novemis.rpgapp.domain.game.Currency
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

        var amount: Int = 0,

        @ManyToOne
        @JoinColumn(name = "balance_id")
        val balance: Balance? = null
)