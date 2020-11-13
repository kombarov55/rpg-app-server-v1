package ru.novemis.rpgapp.repository.game.shop

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.domain.game.shop.ItemForSale

interface ItemForSaleRepository : CrudRepository<ItemForSale, String> {

    @Query("select game.itemsForSale from Game game where game.id = :gameId")
    fun findByGameId(gameId: String): List<ItemForSale>

}