package ru.novemis.rpgapp.converter

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.network.Subnetwork
import ru.novemis.rpgapp.dto.network.SubnetworkDto
import ru.novemis.rpgapp.dto.network.SubnetworkForm
import ru.novemis.rpgapp.repository.network.NetworkRepository
import ru.novemis.rpgapp.util.appendProtocol

@Component
class SubnetworkConverter(
        @Value("\${imgPrefix}")
        private val imgPrefix: String,
        private val networkRepository: NetworkRepository
) {

    fun toDomain(form: SubnetworkForm): Subnetwork {
        return Subnetwork(
                title = form.title,
                description = form.description,
                imgName = form.img,
                backgroundImgName = form.background,
                groupLink = appendProtocol(form.groupLink),
                network = networkRepository.findById(form.networkId).orElseThrow { IllegalArgumentException() }
        )
    }

    fun toDto(subnetwork: Subnetwork): SubnetworkDto {
        return SubnetworkDto(
                id = subnetwork.id,
                title = subnetwork.title,
                description = subnetwork.description,
                imgSrc = subnetwork.imgName,
                backgroundSrc = subnetwork.backgroundImgName,
                groupLink = subnetwork.groupLink
        )
    }


}