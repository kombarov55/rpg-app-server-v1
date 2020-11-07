package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.repository.game.BalanceRepository

@Component
class BalanceService(
        private val repository: BalanceRepository
) {

    fun add(balanceId: String, currency: String, amount: Int) {
        val balance = repository.findById(balanceId).get()

        val editedAmount = balance.amounts.find { it.currency!!.name == currency }!!
        editedAmount.amount += amount

        balance.apply { amounts = this.amounts.filter { it.id == editedAmount.id } + editedAmount }
                .also { repository.save(it) }
    }

    fun subtract(balanceId: String, currency: String, amount: Int) {
        val balance = repository.findById(balanceId).get()

        val editedAmount = balance.amounts.find { it.currency!!.name == currency }!!
        if (editedAmount.amount < amount) {
            throw RuntimeException("Недостаточно средств.")
        }
        editedAmount.amount -= amount

        balance.apply { amounts = this.amounts.filter { it.id == editedAmount.id } + editedAmount }
                .also { repository.save(it) }
    }

    fun transfer(fromBalanceId: String, toBalanceId: String, currency: String, amount: Int) {
        subtract(fromBalanceId, currency, amount)
        add(toBalanceId, currency, amount)
    }
}