package ru.novemis.rpgapp.repository.game.shop

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.domain.game.shop.ItemTemplate

interface ItemTemplateRepository : CrudRepository<ItemTemplate, String> {
    fun findAllByGameId(gameId: String): List<ItemTemplate>

    @Query("from ItemTemplate m where m.game.id = :gameId and m.destination in :destinations")
    fun findAllByGameIdAndDestination(gameId: String, destinations: List<Destination>): List<ItemTemplate>

    fun findByGameIdAndNameStartsWith(gameId: String, name: String): List<ItemTemplate>
}