package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.common.Price
import ru.novemis.rpgapp.repository.game.BalanceRepository
import ru.novemis.rpgapp.repository.game.CurrencyRepository

@Component
class BalanceService(
        private val balanceRepository: BalanceRepository,
        private val currencyRepository: CurrencyRepository
) {

    fun add(gameId: String, balanceId: String, currency: String, amount: Int) {
        val balance = balanceRepository.findById(balanceId).get()

        val editedAmount = balance.amounts.find { it.currency!!.name == currency } ?: Price(currency = currencyRepository.findByGameIdAndName(gameId, currency), amount = 0, balance = balance)
        editedAmount.amount += amount

        balance.apply { amounts = this.amounts.filter { it.id == editedAmount.id } + editedAmount }
                .also { balanceRepository.save(it) }
    }

    fun subtract(gameId: String, balanceId: String, currency: String, amount: Int) {
        val balance = balanceRepository.findById(balanceId).get()

        val editedAmount = balance.amounts.find { it.currency!!.name == currency } ?: Price(currency = currencyRepository.findByGameIdAndName(gameId, currency), amount = 0, balance = balance)
        if (editedAmount.amount < amount) {
            throw RuntimeException("Недостаточно средств.")
        }
        editedAmount.amount -= amount

        balance.apply { amounts = this.amounts.filter { it.id == editedAmount.id } + editedAmount }
                .also { balanceRepository.save(it) }
    }

    fun transfer(gameId: String, fromBalanceId: String, toBalanceId: String, currency: String, amount: Int) {
        subtract(gameId, fromBalanceId, currency, amount)
        add(gameId, toBalanceId, currency, amount)
    }
}