package ru.novemis.rpgapp.converter

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.network.Network
import ru.novemis.rpgapp.dto.network.NetworkDto
import ru.novemis.rpgapp.dto.network.NetworkForm

@Component
class NetworkConverter(
        @Value("\${imgPrefix}")
        private val imgPrefix: String
) {

    fun toDomain(form: NetworkForm): Network {
        return Network(
                title = form.title,
                description = form.description,
                imgName = form.img,
                backgroundImgName = form.background
        )
    }

    fun toDto(network: Network): NetworkDto {
        return NetworkDto(
                id = network.id,
                title = network.title,
                description = network.description,
                imgSrc = imgPrefix + "/" + network.imgName,
                backgroundSrc = imgPrefix + "/" + network.backgroundImgName
        )
    }

}