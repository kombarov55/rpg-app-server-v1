package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.organization.Credit
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto
import ru.novemis.rpgapp.dto.game.organization.dto.CreditDto
import ru.novemis.rpgapp.dto.game.organization.dto.OverdueCreditDto
import java.time.Duration
import java.time.Instant
import java.time.Instant.ofEpochMilli
import java.time.temporal.ChronoUnit
import java.time.temporal.ChronoUnit.DAYS

@Component
class CreditConverter {

    fun toDto(domain: Credit): CreditDto {
        val endingDate = ofEpochMilli(domain.openingDate.time).plus(domain.durationInDays.toLong(), DAYS)

        return CreditDto(
                id = domain.id,
                currencyName = domain.currency!!.name,
                amount = domain.amount,
                debtAmount = domain.debtAmount,
                payedAmount = domain.payedAmount,
                openingDate = domain.openingDate.time,
                endingDate = endingDate.toEpochMilli(),
                durationInDays = domain.durationInDays,
                remainingDays = Duration.between(ofEpochMilli(domain.openingDate.time), endingDate).toDays().toInt(),
                lastPaymentDate = domain.lastPaymentDate?.time,
                organizationName = domain.organization!!.name,
                isOverdue = domain.isOverdue,
                isPaid = domain.isPaid
        )
    }

    fun toOverdueDto(domain: Credit): OverdueCreditDto {
        val creditEndingDate = ofEpochMilli(domain.openingDate.time).plus(domain.durationInDays.toLong(), ChronoUnit.DAYS)

        return OverdueCreditDto(
                id = domain.id,
                owner = GameCharacterShortDto(
                        id = domain.owner!!.id,
                        name = domain.owner.name
                ),
                debtAmount = domain.debtAmount,
                currencyName = domain.currency!!.name,
                overdueDurationInDays = Duration.between(creditEndingDate, Instant.now()).toDays().toInt()
        )
    }

}