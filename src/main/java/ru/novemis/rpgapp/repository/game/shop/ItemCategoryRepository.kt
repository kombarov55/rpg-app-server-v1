package ru.novemis.rpgapp.repository.game.shop

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.shop.ItemCategory

interface ItemCategoryRepository : CrudRepository<ItemCategory, String> {

    fun findByGameId(gameId: String): List<ItemCategory>

}