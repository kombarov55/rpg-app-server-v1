package ru.novemis.rpgapp.domain.game.shop

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Item(
        @Id
        val id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "item_template_id")
        val itemTemplate: ItemTemplate? = null,

        val lvl: Int = 0
)