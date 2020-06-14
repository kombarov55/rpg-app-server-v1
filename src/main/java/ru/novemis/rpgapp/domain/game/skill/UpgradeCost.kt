package ru.novemis.rpgapp.domain.game.skill

import ru.novemis.rpgapp.domain.game.Currency
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


@Entity
data class UpgradeCost(

        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "currency_id")
        var currency: Currency? = null,

        var amount: Int = -1
)