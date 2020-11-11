package ru.novemis.rpgapp.repository.game.shop

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.shop.ItemType

interface ItemTypeRepository : CrudRepository<ItemType, String> {

    fun findByGameId(gameId: String): List<ItemType>

}