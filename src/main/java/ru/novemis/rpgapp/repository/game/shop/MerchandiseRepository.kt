package ru.novemis.rpgapp.repository.game.shop

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.domain.game.shop.Merchandise
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseDto

interface MerchandiseRepository : CrudRepository<Merchandise, String> {
    fun findAllByGameId(gameId: String): List<Merchandise>

    @Query("from Merchandise m where m.game.id = :gameId and m.destination in :destinations")
    fun findAllByGameIdAndDestination(gameId: String, destinations: List<Destination>): List<Merchandise>

    fun findByGameIdAndNameStartsWith(gameId: String, name: String): List<Merchandise>
}