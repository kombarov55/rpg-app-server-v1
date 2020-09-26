package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.organization.Country
import ru.novemis.rpgapp.domain.game.organization.Organization
import ru.novemis.rpgapp.domain.game.organization.OrganizationType
import ru.novemis.rpgapp.dto.game.organization.dto.CountryDto
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationDto
import ru.novemis.rpgapp.dto.game.organization.form.OrganizationForm
import ru.novemis.rpgapp.repository.game.shop.ShopRepository
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository

@Component
class OrganizationConverter(
        private val userAccountRepository: UserAccountRepository,
        private val userAccountConverter: UserAccountConverter,

        private val priceCombinationConverter: PriceCombinationConverter,

        private val shopConverter: ShopConverter,
        private val shopRepository: ShopRepository,

        private val creditOfferConverter: CreditOfferConverter
) {

    fun toDomain(form: OrganizationForm, gameId: String): Organization {
        return when (form.type!!.toDomain()) {
            OrganizationType.COUNTRY -> Country(
                    name = form.name,
                    description = form.description,
                    type = form.type!!.toDomain(),
                    organizationHeads = form.heads.map { userAccountRepository.findById(it.id).get() },
                    initialBudget = form.initialBudget.map { price -> priceCombinationConverter.toDomain(price, gameId) },
                    shops = form.shops.map {
                        it.id?.let { id ->
                            shopRepository.findById(id).orElse(shopConverter.toDomain(it)
                                    .let { shop -> shopRepository.save(shop) })
                        } ?: shopConverter.toDomain(it)
                                .let { shop -> shopRepository.save((shop)) }
                    },

                    entranceTax = priceCombinationConverter.toDomain(form.entranceTax, gameId),
                    incomeTax = form.incomeTax
            )
            else -> Organization(
                    name = form.name,
                    description = form.description,
                    type = form.type!!.toDomain(),
                    organizationHeads = form.heads.map { userAccountRepository.findById(it.id).get() },
                    initialBudget = form.initialBudget.map { price -> priceCombinationConverter.toDomain(price, gameId) },
                    shops = form.shops.map {
                        it.id?.let { id ->
                            shopRepository.findById(id).orElse(shopConverter.toDomain(it)
                                    .let { shop -> shopRepository.save(shop) })
                        } ?: shopConverter.toDomain(it)
                                .let { shop -> shopRepository.save((shop)) }
                    }
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
                        heads = domain.organizationHeads.map { userAccountConverter.toShortDto(it) },
                        initialBudget = domain.initialBudget.map { priceCombinationConverter.toDto(it) },
                        shops = domain.shops.map { shopConverter.toDto(it) },

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
                    heads = domain.organizationHeads.map { userAccountConverter.toShortDto(it) },
                    initialBudget = domain.initialBudget.map { priceCombinationConverter.toDto(it) },
                    shops = domain.shops.map { shopConverter.toDto(it) }
            )
        }
    }

}