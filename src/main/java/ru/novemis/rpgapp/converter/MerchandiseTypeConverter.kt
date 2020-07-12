package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.MerchandiseType
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseTypeDto
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseTypeForm
import ru.novemis.rpgapp.repository.game.shop.ShopRepository

@Component
class MerchandiseTypeConverter(
        private val shopRepository: ShopRepository
) {

    fun toDomain(form: MerchandiseTypeForm, shopId: String): MerchandiseType {
        return MerchandiseType(
                name = form.name,

                shop = shopRepository.findById(shopId).orElseThrow { IllegalArgumentException("shopId is invalid") }
        )
    }

    fun toDto(domain: MerchandiseType): MerchandiseTypeDto {
        return MerchandiseTypeDto(
                id = domain.id,
                name = domain.name
        )
    }

}