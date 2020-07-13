package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.MerchandiseCategory
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseCategoryDto
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseCategoryForm
import ru.novemis.rpgapp.repository.game.GameRepository

@Component
class MerchandiseCategoryConverter(
    private val gameRepository: GameRepository
) {

    fun toDomain(form: MerchandiseCategoryForm, gameId: String): MerchandiseCategory {
        return MerchandiseCategory().apply {
            name = form.name
            game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException("gameId is invalid") }
        }
    }

    fun toDto(domain: MerchandiseCategory): MerchandiseCategoryDto {
        return MerchandiseCategoryDto(
                id = domain.id,
                name = domain.name
        )
    }

}