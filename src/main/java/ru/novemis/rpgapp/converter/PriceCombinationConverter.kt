package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.common.Price
import ru.novemis.rpgapp.domain.game.common.PriceCombination
import ru.novemis.rpgapp.dto.game.common.dto.PriceDto
import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.repository.game.CurrencyRepository

@Component
class PriceCombinationConverter(
        private val currencyRepository: CurrencyRepository
) {

    fun toDomain(listOfPrices: List<PriceForm>, gameId: String): PriceCombination {
        return PriceCombination().apply {
            prices = listOfPrices.map { priceForm ->
                Price(
                        currency = currencyRepository.findByGameIdAndName(gameId, priceForm.name)
                                ?: throw IllegalArgumentException("currency not found"),
                        amount = priceForm.amount
                )
            }
        }
    }

    fun toDto(priceCombination: PriceCombination): List<PriceDto> {
        return priceCombination.prices.map { PriceDto(it.currency!!.name, it.amount) }
    }

    fun toString(priceCombination: PriceCombination): String {
        return priceCombination.prices.map { it.currency!!.name + ": " +  it.amount }.joinToString(" + ")
    }

}