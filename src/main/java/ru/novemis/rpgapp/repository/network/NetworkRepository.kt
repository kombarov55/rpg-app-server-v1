package ru.novemis.rpgapp.repository.network

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.network.Network

interface NetworkRepository : CrudRepository<Network, String> {

    @Query("select n from Network n where n.deleted = false")
    fun findAllExisting(): List<Network>

}