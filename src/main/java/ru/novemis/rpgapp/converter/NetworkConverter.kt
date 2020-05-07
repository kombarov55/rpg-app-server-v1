package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.network.Network
import ru.novemis.rpgapp.dto.network.NetworkDto
import ru.novemis.rpgapp.dto.network.NetworkForm

@Component
class NetworkConverter {

    fun toDomain(form: NetworkForm): Network {
        return Network(
                title = form.title,
                description = form.description
        )
    }

    fun toDto(network: Network): NetworkDto {
        return NetworkDto(
                id = network.id,
                title = network.title,
                description = network.description
        )
    }

}