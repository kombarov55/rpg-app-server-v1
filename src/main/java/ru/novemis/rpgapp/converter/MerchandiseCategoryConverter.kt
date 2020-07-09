package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.MerchandiseCategory
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseCategoryDto
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseCategoryForm
import ru.novemis.rpgapp.repository.game.shop.ShopRepository

@Component
class MerchandiseCategoryConverter(
    private val shopRepository: ShopRepository
) {

    fun toDomain(form: MerchandiseCategoryForm, shopId: String): MerchandiseCategory {
        return MerchandiseCategory().apply {
            name = form.name
            shop = shopRepository.findById(shopId).orElseThrow { IllegalArgumentException("shopId is invalid") }
        }
    }

    fun toDto(domain: MerchandiseCategory): MerchandiseCategoryDto {
        return MerchandiseCategoryDto(
                id = domain.id,
                name = domain.name
        )
    }

}