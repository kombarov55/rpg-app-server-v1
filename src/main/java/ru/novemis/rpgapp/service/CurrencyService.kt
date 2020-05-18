package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.CurrencyConverter
import ru.novemis.rpgapp.dto.game.CurrencyDto
import ru.novemis.rpgapp.repository.game.CurrencyRepository
import ru.novemis.rpgapp.repository.game.GameRepository

@Component
class CurrencyService(
        private val currencyRepository: CurrencyRepository,
        private val currencyConverter: CurrencyConverter,
        private val gameRepository: GameRepository
) {

    fun findByGameId(gameId: String): List<CurrencyDto> {
        return currencyRepository
                .findByGameId(gameId)
                .map { currencyConverter.toDto(it) }
    }
}