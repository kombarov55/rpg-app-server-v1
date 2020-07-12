package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.Shop
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.repository.game.GameRepository
import java.lang.IllegalArgumentException

@Component
class ShopConverter(
        private val gameRepository: GameRepository,
        private val merchandiseCategoryConverter: MerchandiseCategoryConverter,
        private val merchandiseTypeConverter: MerchandiseTypeConverter
) {

    fun toDomain(form: ShopForm, gameId: String): Shop {
        return Shop(
                name = form.name,
                img = form.img,
                type = form.type,

                game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException("gameId is invalid") }
        )
    }

    fun toDto(domain: Shop): ShopDto {
        return ShopDto(
                id = domain.id,
                name = domain.name,
                img = domain.img,
                type = domain.type!!,
                merchandiseCategories = domain.merchandiseCategories.map { merchandiseCategoryConverter.toDto(it) },
                merchandiseTypes = domain.merchandiseTypes.map { merchandiseTypeConverter.toDto(it) }
        )
    }

}