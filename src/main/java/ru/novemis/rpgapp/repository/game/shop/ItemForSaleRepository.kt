package ru.novemis.rpgapp.repository.game.shop

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.domain.game.shop.ItemForSale

interface ItemForSaleRepository : CrudRepository<ItemForSale, String> {

    @Query("select ifs from ItemForSale ifs where ifs.itemTemplate.game.id = :gameId and ifs.itemTemplate.destination = :destination")
    fun findByGameIdAndDesination(gameId: String, destination: Destination): List<ItemForSale>

}