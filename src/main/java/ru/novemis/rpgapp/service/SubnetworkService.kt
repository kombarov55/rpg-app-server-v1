package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.SubnetworkConverter
import ru.novemis.rpgapp.dto.network.SubnetworkDto
import ru.novemis.rpgapp.dto.network.SubnetworkForm
import ru.novemis.rpgapp.repository.network.SubnetworkRepository
import java.util.*
import javax.transaction.Transactional

@Component
open class SubnetworkService(
        private val subnetworkRepository: SubnetworkRepository,
        private val subnetworkConverter: SubnetworkConverter
) {

    open fun save(form: SubnetworkForm): SubnetworkDto {
        return subnetworkConverter.toDto(
                subnetworkRepository.save(
                        subnetworkConverter.toDomain(form)
                )
        )
    }

    open fun update(networkId: String, subnetworkId: String, form: SubnetworkForm): SubnetworkDto {
        return subnetworkConverter.toDto(
                subnetworkRepository.save(
                        subnetworkConverter.toDomain(form.apply { this.networkId = networkId })
                                .apply { id = subnetworkId })
        )
    }

    open fun findByNetworkId(networkId: String): List<SubnetworkDto> {
        return subnetworkRepository.findAllByNetworkId(networkId)
                .map { subnetworkConverter.toDto(it) }
    }

    @Transactional
    open fun delete(subnetworkId: String) {
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