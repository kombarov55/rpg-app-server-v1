package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.organization.Credit
import ru.novemis.rpgapp.dto.game.organization.dto.CreditDto
import java.time.Duration
import java.time.Instant.ofEpochMilli
import java.time.temporal.ChronoUnit.DAYS

@Component
class CreditConverter {

    fun toDto(domain: Credit): CreditDto {
        val endingDate = ofEpochMilli(domain.openingDate.time).plus(domain.durationInDays.toLong(), DAYS)

        return CreditDto(
                id = domain.id,
                currencyName = domain.currency!!.name,
                amount = domain.amount,
                payedAmount = domain.payedAmount,
                openingDate = domain.openingDate.time,
                endingDate = endingDate.toEpochMilli(),
                durationInDays = domain.durationInDays,
                remainingDays = Duration.between(ofEpochMilli(domain.openingDate.time), endingDate).toDays().toInt(),
                minimalPayment = domain.minimalPayment,
                lastPaymentDate = domain.lastPaymentDate?.time,
                organizationName = domain.organization!!.name,
                isOverdue = domain.isOverdue
        )
    }

}