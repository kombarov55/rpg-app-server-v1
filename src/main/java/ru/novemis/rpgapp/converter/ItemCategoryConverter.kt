package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.ItemCategory
import ru.novemis.rpgapp.dto.game.shop.dto.ItemCategoryDto
import ru.novemis.rpgapp.dto.game.shop.form.ItemCategoryForm
import ru.novemis.rpgapp.repository.game.GameRepository

@Component
class ItemCategoryConverter(
    private val gameRepository: GameRepository
) {

    fun toDomain(form: ItemCategoryForm, gameId: String): ItemCategory {
        return ItemCategory().apply {
            name = form.name
            game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException("gameId is invalid") }
        }
    }

    fun toDto(domain: ItemCategory): ItemCategoryDto {
        return ItemCategoryDto(
                id = domain.id,
                name = domain.name
        )
    }

}