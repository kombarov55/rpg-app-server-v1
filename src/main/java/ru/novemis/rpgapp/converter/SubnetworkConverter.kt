package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.network.Subnetwork
import ru.novemis.rpgapp.dto.network.SubnetworkDto
import ru.novemis.rpgapp.dto.network.SubnetworkForm
import ru.novemis.rpgapp.repository.network.NetworkRepository

@Component
class SubnetworkConverter(
        private val networkRepository: NetworkRepository
) {

    fun toDomain(form: SubnetworkForm): Subnetwork {
        return Subnetwork(
                title = form.title,
                description = form.description,
                network = networkRepository.findById(form.networkId).orElseThrow { IllegalArgumentException() }
        )
    }

    fun toDto(subnetwork: Subnetwork): SubnetworkDto {
        return SubnetworkDto(
                id = subnetwork.id,
                title = subnetwork.title,
                description = subnetwork.description,
                imgSrc = "https://sun9-64.userapi.com/c858416/v858416297/1c6f50/HpIP0jOcov4.jpg"
        )
    }


}