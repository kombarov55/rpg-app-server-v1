package ru.novemis.rpgapp.domain.game.shop

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class WarehouseEntry(

        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        var itemTemplate: ItemTemplate? = null,

        var amount: Int = 0
)