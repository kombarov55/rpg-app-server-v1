package ru.novemis.rpgapp.repository.network

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.network.Currency

interface CurrencyRepository : CrudRepository<Currency, String> {

    @Query("select c from Currency c where c.game.id = :gameId and c.name = :name")
    fun findByGameIdAndName(gameId: String, name: String): Currency?

}