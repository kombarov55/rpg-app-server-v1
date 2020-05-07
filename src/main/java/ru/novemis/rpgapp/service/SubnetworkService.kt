package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.SubnetworkConverter
import ru.novemis.rpgapp.dto.network.SubnetworkDto
import ru.novemis.rpgapp.dto.network.SubnetworkForm
import ru.novemis.rpgapp.repository.network.SubnetworkRepository
import java.util.*

@Component
class SubnetworkService(
        private val subnetworkRepository: SubnetworkRepository,
        private val subnetworkConverter: SubnetworkConverter
) {

    fun save(form: SubnetworkForm): SubnetworkDto {
        return subnetworkConverter.toDto(
                subnetworkRepository.save(
                        subnetworkConverter.toDomain(form)
                )
        )
    }

    fun findByNetworkId(networkId: String): List<SubnetworkDto> {
        return subnetworkRepository.findAllByNetworkId(networkId)
                .map { subnetworkConverter.toDto(it) }
    }

    fun delete(subnetworkId: String) {
        val subnetwork = subnetworkRepository.findById(subnetworkId).orElseThrow { IllegalArgumentException() }
        val deletionDate = Date()

        subnetwork.deleted = true
        subnetwork.deletionDate = deletionDate
        subnetwork.games.forEach {
            it.deleted = true
            it.deletionDate = deletionDate
        }

        subnetworkRepository.save(subnetwork)
    }

}