package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.repository.game.BalanceRepository

@Component
class BalanceService(
        private val repository: BalanceRepository
) {
    
    fun transfer(fromBalanceId: String, toBalanceId: String, currency: String, amount: Int) {
        val from = repository.findById(fromBalanceId).get()
        val to = repository.findById(toBalanceId).get()

        val amountToSubtract = from.amounts.find { it.currency!!.name == currency }!!
        val amountToAdd = to.amounts.find { it.currency!!.name == currency }!!

        amountToSubtract.amount -= amount
        amountToAdd.amount += amount

        from.apply { amounts = from.amounts.filter { it.id !== amountToSubtract.id } + amountToSubtract }
                .also { repository.save(it) }

        to.apply { amounts = from.amounts.filter { it.id !== amountToAdd.id } + amountToAdd }
                .also { repository.save(it) }
    }
}