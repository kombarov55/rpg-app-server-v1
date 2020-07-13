package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.MerchandiseType
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseTypeDto
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseTypeForm
import ru.novemis.rpgapp.repository.game.GameRepository

@Component
class MerchandiseTypeConverter(
        private val gameRepository: GameRepository
) {

    fun toDomain(form: MerchandiseTypeForm, gameId: String): MerchandiseType {
        return MerchandiseType(
                name = form.name,

                game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException("gameId is invalid") }
        )
    }

    fun toDto(domain: MerchandiseType): MerchandiseTypeDto {
        return MerchandiseTypeDto(
                id = domain.id,
                name = domain.name
        )
    }

}