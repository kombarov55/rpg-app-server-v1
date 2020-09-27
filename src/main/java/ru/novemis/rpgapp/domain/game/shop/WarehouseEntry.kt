package ru.novemis.rpgapp.domain.game.shop

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class WarehouseEntry(

        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        var merchandise: Merchandise? = null,

        var amount: Int = 0
)