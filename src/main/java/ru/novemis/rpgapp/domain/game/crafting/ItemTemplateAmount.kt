package ru.novemis.rpgapp.domain.game.crafting

import ru.novemis.rpgapp.domain.game.shop.ItemTemplate
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class ItemTemplateAmount(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "item_template_id")
        var itemTemplate: ItemTemplate? = null,

        var amount: Int = 1,

        @ManyToOne
        @JoinColumn(name = "recipe_id")
        var recipe: Recipe? = null
)