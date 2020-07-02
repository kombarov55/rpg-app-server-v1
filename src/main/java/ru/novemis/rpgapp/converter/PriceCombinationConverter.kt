package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.common.Price
import ru.novemis.rpgapp.domain.game.common.PriceCombination
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

}