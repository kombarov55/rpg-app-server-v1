package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.ItemForSaleConverter
import ru.novemis.rpgapp.converter.ShopConverter
import ru.novemis.rpgapp.domain.game.shop.Merchandise
import ru.novemis.rpgapp.domain.game.shop.MerchandiseToLvl
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.game.shop.form.ItemForSaleForm
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.shop.MerchandiseRepository
import ru.novemis.rpgapp.repository.game.shop.ShopRepository
import javax.transaction.Transactional

@Component
open class ShopService(
        private val repository: ShopRepository,
        private val converter: ShopConverter,
        private val gameRepository: GameRepository,
        private val characterRepository: GameCharacterRepository,
        private val merchandiseRepository: MerchandiseRepository
) {

    @Transactional
    open fun update(form: ShopForm, gameId: String, shopId: String): ShopDto {
        return form
                .let { converter.toDomain(form) }
                .apply { id = shopId }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }



    @Transactional
    open fun transferItemFromGame(gameId: String, destinationCharacterId: String, merchandiseId: String) {
        val game = gameRepository.findById(gameId).get()
        game.itemsForSale = game.itemsForSale.filter { it.id != merchandiseId }
        gameRepository.save(game)

        val character = characterRepository.findById(destinationCharacterId).get()
        val merchandise = merchandiseRepository.findById(merchandiseId).get()
        character.ownedMerchandise = character.ownedMerchandise + merchandise
        characterRepository.save(character)
    }


}