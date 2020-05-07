package ru.novemis.rpgapp.repository.network

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.network.Game

interface GameRepository : CrudRepository<Game, String> {

    fun findByNetworkId(networkId: String): List<Game>

    fun findBySubnetworkId(subnetworkId: String): List<Game>

}