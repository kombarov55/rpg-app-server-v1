package ru.novemis.rpgapp.domain.game.shop

import ru.novemis.rpgapp.domain.game.common.PriceCombination
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity
data class ItemForSale(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @OneToOne
        @JoinColumn(name = "merchandise_id")
        var merchandise: Merchandise? = null,

        @OneToOne
        var price: PriceCombination? = null
)