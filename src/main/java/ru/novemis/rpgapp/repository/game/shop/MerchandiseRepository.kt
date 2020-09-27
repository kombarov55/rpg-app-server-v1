package ru.novemis.rpgapp.repository.game.shop

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.domain.game.shop.Merchandise

interface MerchandiseRepository : CrudRepository<Merchandise, String> {
    fun findAllByGameId(gameId: String): List<Merchandise>

    fun findAllByGameIdAndDestination(gameId: String, destination: Destination): List<Merchandise>
}