package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.NetworkConverter
import ru.novemis.rpgapp.dto.network.NetworkDto
import ru.novemis.rpgapp.dto.network.NetworkForm
import ru.novemis.rpgapp.repository.network.NetworkRepository
import java.util.*

@Component
class NetworkService(
        private val networkRepository: NetworkRepository,
        private val networkConverter: NetworkConverter
) {

    fun save(form: NetworkForm): NetworkDto {
        return networkConverter.toDto(
                networkRepository.save(
                        networkConverter.toDomain(form)
                )
        )
    }

    fun getAll(): List<NetworkDto> {
        return networkRepository.findAll()
                .map { networkConverter.toDto(it) }
    }

    fun delete(networkId: String) {
        val network = networkRepository.findById(networkId).orElseThrow { throw IllegalArgumentException() }
        network.deleted = true
        network.deletionDate = Date()

        networkRepository.save(network)
    }
}
