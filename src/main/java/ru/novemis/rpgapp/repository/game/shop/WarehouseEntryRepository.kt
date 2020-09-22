package ru.novemis.rpgapp.repository.game.shop

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.shop.WarehouseEntry

interface WarehouseEntryRepository : CrudRepository<WarehouseEntry, String> {

    fun findAllByShopId(shopId: String): List<WarehouseEntry>

}