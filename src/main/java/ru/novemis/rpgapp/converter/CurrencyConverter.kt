package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.Currency
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.dto.game.dto.CurrencyDto
import ru.novemis.rpgapp.dto.game.form.CurrencyForm
import ru.novemis.rpgapp.repository.game.CurrencyRepository
import ru.novemis.rpgapp.repository.game.GameRepository

@Component
class CurrencyConverter(
        private val currencyRepository: CurrencyRepository,
        private val gameRepository: GameRepository
) {

    fun toDomain(gameId: String, currencyForm: CurrencyForm): Currency {
        return Currency(
                name = currencyForm.name,
                priceInActivityPoints = currencyForm.priceInActivityPoints,
                game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException("gameId is invalid") }
        )
    }

    fun toDomainOrExisting(game: Game, currencyForm: CurrencyForm): Currency {
        return currencyForm.id
                ?.let { currencyRepository.findById(it).orElseThrow { java.lang.IllegalArgumentException() } }
                ?: Currency(
                        name = currencyForm.name,
                        priceInActivityPoints = currencyForm.priceInActivityPoints,
                        game = game
                )
    }

    fun toDto(currency: Currency): CurrencyDto {
        return CurrencyDto(
                id = currency.id,
                name = currency.name,
                priceInActivityPoints = currency.priceInActivityPoints
        )
    }

}