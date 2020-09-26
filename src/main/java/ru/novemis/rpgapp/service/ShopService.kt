package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.ShopConverter
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.repository.game.shop.ShopRepository
import javax.transaction.Transactional

@Component
open class ShopService(
        private val shopRepository: ShopRepository,
        private val shopConverter: ShopConverter
) {

    @Transactional
    open fun save(shopForm: ShopForm, gameId: String): ShopDto {
        return shopForm
                .let { shopConverter.toDomain(it) }
                .let { shopRepository.save(it) }
                .let { shopConverter.toDto(it) }
    }

    @Transactional
    open fun update(form: ShopForm, gameId: String, shopId: String): ShopDto {
        return form
                .let { shopConverter.toDomain(form) }
                .apply { id = shopId }
                .let { shopRepository.save(it) }
                .let { shopConverter.toDto(it) }
    }

    @Transactional
    open fun delete(id: String): ShopDto {
        return shopRepository.findById(id).orElseThrow { IllegalArgumentException() }
                .also { shopRepository.delete(it) }
                .let { shopConverter.toDto(it) }
    }

}