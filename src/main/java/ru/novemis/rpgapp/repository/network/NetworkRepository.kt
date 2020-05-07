package ru.novemis.rpgapp.repository.network

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.network.Network

interface NetworkRepository : CrudRepository<Network, String> {
}