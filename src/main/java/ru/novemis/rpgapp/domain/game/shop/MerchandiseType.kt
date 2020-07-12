package ru.novemis.rpgapp.domain.game.shop

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class MerchandiseType(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        @ManyToOne
        @JoinColumn(name = "shop_id")
        var shop: Shop? = null
)