package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.ShopConverter
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.shop.ShopRepository
import javax.transaction.Transactional

@Component
open class ShopService(
        private val shopRepository: ShopRepository,
        private val shopConverter: ShopConverter,

        private val gameRepository: GameRepository
) {

    @Transactional
    open fun getAllByGameId(gameId: String): List<ShopDto> {
        return gameRepository.findById(gameId).get()
                .shops.map { shopConverter.toDto(it) }
    }

    @Transactional
    open fun save(shopForm: ShopForm, gameId: String): ShopDto {
        return shopForm
                .let { shopConverter.toDomain(it) }
                .let { shopRepository.save(it) }
                .also { shop ->
                    gameRepository.findById(gameId).get()
                            .apply { shops += shop }
                            .let { gameRepository.save(it) }
                }
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
    open fun delete(id: String, gameId: String): ShopDto {
        return shopRepository.findById(id).orElseThrow { IllegalArgumentException() }
                .also {
                    gameRepository.findById(gameId).get()
                            .apply { shops = shops.filter { it.id !== id } }
                            .let { gameRepository.save(it) }
                }
                .also { shopRepository.delete(it) }
                .let { shopConverter.toDto(it) }
    }

}