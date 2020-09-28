package ru.novemis.rpgapp.domain.game.shop

import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Shop(
        @Id
        var id: String = UUID.randomUUID().toString(),
        var name: String = "",
        var img: String = "",
        var type: ShopType? = null,

        @OneToMany(orphanRemoval = true, cascade = [CascadeType.ALL])
        var itemsForSale: List<ItemForSale> = mutableListOf()

//        @OneToMany(orphanRemoval = true, cascade = [CascadeType.ALL])
//        var warehouseEntries: List<WarehouseEntry> = mutableListOf()
)