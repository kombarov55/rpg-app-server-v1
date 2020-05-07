package ru.novemis.rpgapp.repository.network

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.network.Subnetwork

interface SubnetworkRepository : CrudRepository<Subnetwork, String> {

    fun findAllByNetworkId(networkId: String): List<Subnetwork>

}