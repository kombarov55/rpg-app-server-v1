package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.common.Balance
import ru.novemis.rpgapp.domain.game.common.Price
import ru.novemis.rpgapp.domain.game.organization.Organization
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationDto
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationShortDto
import ru.novemis.rpgapp.dto.game.organization.form.OrganizationForm
import ru.novemis.rpgapp.repository.game.CurrencyRepository

@Component
class OrganizationConverter(
        private val priceCombinationConverter: PriceCombinationConverter,
        private val shopConverter: ShopConverter,
        private val creditOfferConverter: CreditOfferConverter,
        private val warehouseEntryConverter: WarehouseEntryConverter,
        private val currencyRepository: CurrencyRepository
) {

    fun toDomain(form: OrganizationForm, gameId: String): Organization {
        return Organization(
                name = form.name,
                description = form.description,
                type = form.type,
                balance = Balance(amounts = currencyRepository.findAllByGameId(gameId).map { currency -> Price(currency = currency, amount = 0) }),
                game = Game(id = gameId)
        )
    }

    fun toDto(domain: Organization): OrganizationDto {
        return OrganizationDto(
                id = domain.id,
                name = domain.name,
                description = domain.description,
                type = domain.type,
                heads = domain.heads.map { GameCharacterShortDto(
                        id = it.id,
                        name = it.name
                ) },
                balance = domain.balance!!.amounts.map { priceCombinationConverter.toDto(it) },
                shops = domain.shops.map { shopConverter.toShortDto(it) },
                ownedMerchandise = domain.ownedMerchandise.map { warehouseEntryConverter.toDto(it) },
                entranceTax = domain.entranceTax?.let { priceCombinationConverter.toDto(it) },
                incomeTax = domain.incomeTax,
                creditOffers = domain.creditOffers.map { creditOfferConverter.toDto(it) }
        )
    }

    fun toShortDto(domain: Organization): OrganizationShortDto {
        return OrganizationShortDto(
                id = domain.id,
                name = domain.name,
                description = domain.description,
                type = domain.type,
                balanceId = domain.balance!!.id
        )

    }

}