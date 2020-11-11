package ru.novemis.rpgapp.domain.game.shop

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.domain.game.common.PriceCombination
import java.util.*
import javax.persistence.*

@Entity
data class ItemForSale(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @OneToOne
        @JoinColumn(name = "item_template_id")
        var itemTemplate: ItemTemplate? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        var price: PriceCombination? = null,

        var creationDate: Date = Date(),

        @ManyToOne
        @JoinColumn(name = "shop_id")
        var shop: Shop? = null,

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null,

        var ownerType: ItemForSaleOwner = ItemForSaleOwner.GAME,

        @ManyToOne
        @JoinColumn(name = "owner_character_id")
        var owner: GameCharacter? = null

)