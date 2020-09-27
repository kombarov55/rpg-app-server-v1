package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.common.Price

@Component
class CalcService {

    fun sum(balance1: List<Price>, balance2: List<Price>): List<Price> {
        val groupedBalances = (balance1 + balance2).groupBy { it.currency }

        return groupedBalances.map { pair ->
            val currency = pair.key
            val amounts = pair.value

            val sum = amounts.map { it.amount }.sum()

            Price(
                    currency = currency,
                    amount = sum
            )
        }
    }

}