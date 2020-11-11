package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.ItemType
import ru.novemis.rpgapp.dto.game.shop.dto.ItemTypeDto
import ru.novemis.rpgapp.dto.game.shop.form.ItemTypeForm
import ru.novemis.rpgapp.repository.game.GameRepository

@Component
class ItemTypeConverter(
        private val gameRepository: GameRepository
) {

    fun toDomain(form: ItemTypeForm, gameId: String): ItemType {
        return ItemType(
                name = form.name,

                game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException("gameId is invalid") }
        )
    }

    fun toDto(domain: ItemType): ItemTypeDto {
        return ItemTypeDto(
                id = domain.id,
                name = domain.name
        )
    }

}