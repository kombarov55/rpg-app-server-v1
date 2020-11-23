package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.common.Price
import ru.novemis.rpgapp.repository.game.BalanceRepository
import ru.novemis.rpgapp.repository.game.ConversionRepository
import ru.novemis.rpgapp.repository.game.CurrencyRepository
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository

@Component
class BalanceService(
        private val balanceRepository: BalanceRepository,
        private val currencyRepository: CurrencyRepository,
        private val conversionRepository: ConversionRepository,
        private val gameCharacterRepository: GameCharacterRepository
) {

    fun add(gameId: String, balanceId: String, currency: String, amount: Int) {
        val balance = balanceRepository.findById(balanceId).get()

        val editedAmount = balance.amounts.find { it.currency!!.name == currency } ?: Price(currency = currencyRepository.findByGameIdAndName(gameId, currency), amount = 0, balance = balance)
        editedAmount.amount += amount

        balance.amounts = balance.amounts.filter { it.id == editedAmount.id } + editedAmount
        balanceRepository.save(balance)
    }

    fun subtract(gameId: String, balanceId: String, currency: String, amount: Int) {
        val balance = balanceRepository.findById(balanceId).get()

        val editedAmount = balance.amounts.find { it.currency!!.name == currency } ?: Price(currency = currencyRepository.findByGameIdAndName(gameId, currency), amount = 0, balance = balance)
        if (editedAmount.amount < amount) {
            throw RuntimeException("Недостаточно средств.")
        }
        editedAmount.amount -= amount

        balance.apply { amounts = this.amounts.filter { it.id != editedAmount.id } + editedAmount }
                .also { balanceRepository.save(it) }
    }

    fun transfer(gameId: String, fromBalanceId: String, toBalanceId: String, currency: String, amount: Int) {
        subtract(gameId, fromBalanceId, currency, amount)
        add(gameId, toBalanceId, currency, amount)
    }

    fun convert(currency1Id: String, currency2Id: String, amount: Int, characterId: String) {
        val character = gameCharacterRepository.findById(characterId).get()
        val gameId = character.game!!.id
        val currency1 = currencyRepository.findById(currency1Id).get()
        val currency2 = currencyRepository.findById(currency2Id).get()

        val conversion = conversionRepository.findByGameId(gameId).find {
            (it.currency1!!.id == currency1Id && it.currency2!!.id == currency2Id) ||
            (it.currency1!!.id == currency2Id && it.currency2!!.id == currency1Id)
        }!!

        val rate = if (conversion.currency1!!.id == currency1Id) conversion.conversionPrice1to2 else conversion.conversionPrice2to1
        val amountToAdd = (amount.toDouble() * rate).toInt()

        subtract(gameId, character.balance!!.id, currency1.name, amount)
        add(gameId, character.balance!!.id, currency2.name, amountToAdd)
    }
}