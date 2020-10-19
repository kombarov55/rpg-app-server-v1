package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.CurrencyConverter
import ru.novemis.rpgapp.dto.game.dto.CurrencyDto
import ru.novemis.rpgapp.dto.game.form.CurrencyForm
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
                .findAllByGameId(gameId)
                .map { currencyConverter.toDto(it) }
    }

    fun save(currencyForm: CurrencyForm, gameId: String): CurrencyDto {
        return gameRepository.findById(gameId).map { game ->
            currencyForm
                    .let { currencyConverter.toDomainOrExisting(game, currencyForm) }
                    .let { currencyRepository.save(it) }
                    .let { currencyConverter.toDto(it) }
        }.orElseThrow { IllegalArgumentException("game id is invalid") }
    }

    fun update(currencyForm: CurrencyForm, gameId: String, currencyId: String): CurrencyDto {
        val currency = currencyRepository.findById(currencyId).orElseThrow { IllegalArgumentException("currencyId is invalid") }

        return currencyConverter.toDomain(gameId, currencyForm)
                .apply { id = currency.id }
                .let { currencyRepository.save(it) }
                .let { currencyConverter.toDto(it) }
    }
}