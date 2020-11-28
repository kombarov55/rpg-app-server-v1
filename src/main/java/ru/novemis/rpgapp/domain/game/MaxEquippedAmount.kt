package ru.novemis.rpgapp.domain.game

import ru.novemis.rpgapp.domain.game.shop.ItemCategory
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class MaxEquippedAmount(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "game_settings_id")
        var gameSettings: GameSettings? = null,

        @ManyToOne
        @JoinColumn(name = "item_category_id")
        var itemCategory: ItemCategory? = null,

        var amount: Int = 1
)