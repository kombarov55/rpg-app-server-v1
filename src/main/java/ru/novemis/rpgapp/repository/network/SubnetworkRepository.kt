package ru.novemis.rpgapp.repository.network

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.network.Subnetwork

interface SubnetworkRepository : CrudRepository<Subnetwork, String> {

    @Query("select s from Subnetwork s where s.deleted = false and s.network.id = :networkId")
    fun findAllByNetworkId(networkId: String): List<Subnetwork>

}