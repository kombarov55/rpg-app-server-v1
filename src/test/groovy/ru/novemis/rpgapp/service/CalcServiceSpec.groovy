package ru.novemis.rpgapp.service

import ru.novemis.rpgapp.domain.game.Currency
import ru.novemis.rpgapp.domain.game.common.Price
import spock.lang.Specification

class CalcServiceSpec extends Specification {

    def calcService = new CalcService()

    def "add two balances"() {
        given:
        def currency1 = new Currency("", "Золото", 0, null)
        def currency2 = new Currency("", "Серебро", 0, null)

        def balance1 = [
                new Price("", currency1, 100),
        ]

        def balance2 = [
                new Price("", currency1, 500),
                new Price("", currency2, 400)
        ]

        when: "adding two balances"
        def result = calcService.sum(balance1, balance2)

        then: "result is correct"
        result.find {it.currency.name == currency1.name  }.amount == 600
        result.find { it.currency.name == currency2.name }.amount == 400

        result.size() == 2

        println result
    }

}
