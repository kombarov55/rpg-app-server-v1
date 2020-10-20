package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.common.Balance
import ru.novemis.rpgapp.domain.game.organization.Country
import ru.novemis.rpgapp.domain.game.organization.Organization
import ru.novemis.rpgapp.domain.game.organization.OrganizationType
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto
import ru.novemis.rpgapp.dto.game.organization.dto.CountryDto
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationDto
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationShortDto
import ru.novemis.rpgapp.dto.game.organization.form.OrganizationForm
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.shop.ShopRepository

@Component
class OrganizationConverter(
        private val gameCharacterRepository: GameCharacterRepository,

        private val priceCombinationConverter: PriceCombinationConverter,

        private val shopConverter: ShopConverter,
        private val shopRepository: ShopRepository,

        private val creditOfferConverter: CreditOfferConverter,

        private val warehouseEntryConverter: WarehouseEntryConverter
) {

    fun toDomain(form: OrganizationForm, gameId: String): Organization {
        return when (form.type!!.toDomain()) {
            OrganizationType.COUNTRY -> Country(
                    name = form.name,
                    description = form.description,
                    type = form.type!!.toDomain(),
                    organizationHeads = form.heads.map { gameCharacterRepository.findById(it.id).get() },
                    balance = Balance(amounts = form.balance.map { price -> priceCombinationConverter.toDomain(price, gameId) }),
                    shops = form.shops.map {
                        it.id?.let { id ->
                            shopRepository.findById(id).orElse(shopConverter.toDomain(it)
                                    .let { shop -> shopRepository.save(shop) })
                        } ?: shopConverter.toDomain(it)
                                .let { shop -> shopRepository.save((shop)) }
                    },
                    ownedMerchandise = form.ownedMerchandise.map { warehouseEntryConverter.toDomain(it) },

                    entranceTax = priceCombinationConverter.toDomain(form.entranceTax, gameId),
                    incomeTax = form.incomeTax
            )
            else -> Organization(
                    name = form.name,
                    description = form.description,
                    type = form.type!!.toDomain(),
                    organizationHeads = form.heads.map { gameCharacterRepository.findById(it.id).get() },
                    balance = Balance(amounts = form.balance.map { price -> priceCombinationConverter.toDomain(price, gameId) }),
                    shops = form.shops.map {
                        it.id?.let { id ->
                            shopRepository.findById(id).orElse(shopConverter.toDomain(it)
                                    .let { shop -> shopRepository.save(shop) })
                        } ?: shopConverter.toDomain(it)
                                .let { shop -> shopRepository.save((shop)) }
                    },
                    ownedMerchandise = form.ownedMerchandise.map { warehouseEntryConverter.toDomain(it) }
            )
        }
    }

    fun toDto(domain: Organization): OrganizationDto {
        return when (domain.type) {
            OrganizationType.COUNTRY -> domain.let { domain as Country }.let { domain ->
                CountryDto(
                        id = domain.id,
                        name = domain.name,
                        description = domain.description,
                        type = domain.type!!.toDto(),
                        heads = domain.organizationHeads.map { GameCharacterShortDto(
                                id = it.id,
                                name = it.name
                        ) },
                        balance = domain.balance!!.amounts.map { priceCombinationConverter.toDto(it) },
                        shops = domain.shops.map { shopConverter.toDto(it) },
                        ownedMerchandise = domain.ownedMerchandise.map { warehouseEntryConverter.toDto(it) },

                        entranceTax = domain.entranceTax?.let { priceCombinationConverter.toDto(it) } ?: emptyList(),
                        incomeTax = domain.incomeTax,
                        creditOffers = domain.creditOffers.map { creditOfferConverter.toDto(it) }
                )
            }
            else -> OrganizationDto(
                    id = domain.id,
                    name = domain.name,
                    description = domain.description,
                    type = domain.type!!.toDto(),
                    heads = domain.organizationHeads.map { GameCharacterShortDto(
                            id = it.id,
                            name = it.name
                    ) },
                    balance = domain.balance!!.amounts.map { priceCombinationConverter.toDto(it) },
                    shops = domain.shops.map { shopConverter.toDto(it) },
                    ownedMerchandise = domain.ownedMerchandise.map { warehouseEntryConverter.toDto(it) }
            )
        }
    }

    fun toShortDto(domain: Organization): OrganizationShortDto {
        return OrganizationShortDto(
                id = domain.id,
                name = domain.name,
                description = domain.description,
                type = domain.type!!
        )

    }

}