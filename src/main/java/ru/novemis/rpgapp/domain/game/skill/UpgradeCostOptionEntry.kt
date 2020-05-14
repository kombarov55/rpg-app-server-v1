package ru.novemis.rpgapp.domain.game.skill

import ru.novemis.rpgapp.domain.game.Currency
import ru.novemis.rpgapp.domain.game.skill.UpgradeCostOption
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class UpgradeCostOptionEntry(

        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "upgrade_cost_option_id")
        var upgradeCostOption: UpgradeCostOption? = null,

        @ManyToOne
        @JoinColumn(name = "currency_id")
        var currency: Currency? = null,

        var amount: Int = -1
)