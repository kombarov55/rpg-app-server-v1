package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.ShopConverter
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.repository.game.shop.ShopRepository

@Component
class ShopService(
        private val shopRepository: ShopRepository,
        private val shopConverter: ShopConverter
) {

    fun save(shopForm: ShopForm, gameId: String): ShopDto {
        return shopForm
                .let { shopConverter.toDomain(it, gameId) }
                .let { shopRepository.save(it) }
                .let { shopConverter.toDto(it) }
    }
}