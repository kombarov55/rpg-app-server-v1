package ru.novemis.rpgapp.domain.game.shop

import ru.novemis.rpgapp.domain.game.organization.Organization
import java.util.*
import javax.persistence.*

@Entity
data class Shop(
        @Id
        var id: String = UUID.randomUUID().toString(),
        var name: String = "",
        var img: String = "",
        var type: ShopType? = null,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "shop")
        var itemsForSale: List<ItemForSale> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "organization_id")
        var organization: Organization? = null
)