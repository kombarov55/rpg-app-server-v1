package ru.novemis.rpgapp.repository.game

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.Game

interface GameRepository : CrudRepository<Game, String> {

    @Query("select g from Game g where g.deleted = false and g.network.id = :networkId")
    fun findByNetworkId(networkId: String): List<Game>

    @Query("select g from Game g where g.deleted = false and g.subnetwork.id = :subnetworkId")
    fun findBySubnetworkId(subnetworkId: String): List<Game>

    @Query("select g from Game g where g.network = null and g.subnetwork = null")
    fun findOpenGames(): List<Game>

}