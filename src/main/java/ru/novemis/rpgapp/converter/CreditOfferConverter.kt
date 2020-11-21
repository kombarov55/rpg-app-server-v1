package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.organization.CreditOffer
import ru.novemis.rpgapp.dto.game.organization.dto.CreditOfferDto
import ru.novemis.rpgapp.dto.game.organization.form.CreditOfferForm
import ru.novemis.rpgapp.repository.game.CurrencyRepository

@Component
class CreditOfferConverter(
        private val currencyRepository: CurrencyRepository,
        private val currencyConverter: CurrencyConverter
) {

    fun toDomain(form: CreditOfferForm): CreditOffer {
        return CreditOffer(
                name = form.name,
                description = form.description,
                currency = currencyRepository.findById(form.currency!!.id!!).get(),
                minAmount = form.minAmount,
                maxAmount = form.maxAmount,
                rate = form.rate,
                minDurationInDays = form.minDurationInDays,
                maxDurationInDays = form.maxDurationInDays
        )
    }

    fun toDto(domain: CreditOffer): CreditOfferDto {
        return CreditOfferDto(
                id = domain.id,
                name = domain.name,
                description = domain.description,
                currency = currencyConverter.toDto(domain.currency!!),
                minAmount = domain.minAmount,
                maxAmount = domain.maxAmount,
                rate = domain.rate,
                minDurationInDays = domain.minDurationInDays,
                maxDurationInDays = domain.maxDurationInDays
        )
    }
}