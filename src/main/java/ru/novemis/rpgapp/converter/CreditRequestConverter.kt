package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.organization.CreditRequest
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto
import ru.novemis.rpgapp.dto.game.organization.form.CreditRequestDto

@Component
class CreditRequestConverter {
    fun toDto(domain: CreditRequest): CreditRequestDto {
        return CreditRequestDto(
                id = domain.id,
                duration = domain.duration,
                amount = domain.amount,
                currencyName = domain.currency!!.name,
                rate = domain.creditOffer!!.rate,
                purpose = domain.purpose,
                status = domain.status,
                requester = GameCharacterShortDto(
                        id = domain.requester!!.id,
                        name = domain.requester.name
                )
        )
    }
}