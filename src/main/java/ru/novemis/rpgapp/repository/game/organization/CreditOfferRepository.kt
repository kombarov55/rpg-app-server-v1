package ru.novemis.rpgapp.repository.game.organization

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.organization.CreditOffer

interface CreditOfferRepository : CrudRepository<CreditOffer, String> {
}