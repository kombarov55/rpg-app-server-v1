package ru.novemis.rpgapp.domain.game.shop

import ru.novemis.rpgapp.domain.game.Game
import java.util.*
import javax.persistence.*

@Entity
data class Shop(
        @Id
        var id: String = UUID.randomUUID().toString(),
        var name: String = "",
        var img: String = "",
        var type: ShopType = ShopType.PLAYERS,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "shop")
        var merchandiseCategories: List<MerchandiseCategory> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
)