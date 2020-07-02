package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.Currency
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.dto.game.CurrencyDto
import ru.novemis.rpgapp.dto.game.CurrencyForm
import ru.novemis.rpgapp.repository.game.CurrencyRepository

@Component
class CurrencyConverter(
        private val currencyRepository: CurrencyRepository
) {

    fun toDomain(game: Game, currencyForm: CurrencyForm): Currency {
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