package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.NetworkConverter
import ru.novemis.rpgapp.dto.network.NetworkDto
import ru.novemis.rpgapp.dto.network.NetworkForm
import ru.novemis.rpgapp.repository.network.NetworkRepository
import java.util.*
import javax.transaction.Transactional

@Component
open class NetworkService(
        private val networkRepository: NetworkRepository,
        private val networkConverter: NetworkConverter
) {

    open fun save(form: NetworkForm): NetworkDto {
        return networkConverter.toDto(
                networkRepository.save(
                        networkConverter.toDomain(form)
                )
        )
    }

    open fun update(networkId: String, form: NetworkForm): NetworkDto {
        return networkConverter.toDto(
                networkRepository.save(
                        networkConverter.toDomain(form)
                                .apply { id = networkId }
                )
        )
    }

    open fun getAll(): List<NetworkDto> {
        return networkRepository.findAllExisting()
                .map { networkConverter.toDto(it) }
    }

    @Transactional
    open fun delete(networkId: String) {
        val network = networkRepository.findById(networkId).orElseThrow { throw IllegalArgumentException() }

        val deletionDate = Date()

        network.deleted = true
        network.deletionDate = deletionDate
        network.subnetworks.forEach {
            it.deleted = true
            it.deletionDate = deletionDate
        }
        network.games.forEach {
            it.deleted = true
            it.deletionDate = deletionDate
        }

        networkRepository.save(network)
    }
}
