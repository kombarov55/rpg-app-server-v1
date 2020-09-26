package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.CreditOfferConverter
import ru.novemis.rpgapp.converter.OrganizationConverter
import ru.novemis.rpgapp.converter.ShopConverter
import ru.novemis.rpgapp.domain.game.organization.Country
import ru.novemis.rpgapp.dto.game.organization.dto.CountryDto
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationDto
import ru.novemis.rpgapp.dto.game.organization.form.CreditOfferForm
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import javax.transaction.Transactional

@RestController
open class CountryController(
        private val repository: OrganizationRepository,
        private val converter: OrganizationConverter,

        private val shopConverter: ShopConverter,
        private val creditOfferConverter: CreditOfferConverter
) {

    @PostMapping("/organization/{id}/shop")
    @Transactional
    open fun addShop(
            @PathVariable("id") id: String,
            @RequestBody form: ShopForm
    ): OrganizationDto {
        return repository.findById(id).orElseThrow { IllegalArgumentException() }
                .apply { shops += shopConverter.toDomain(form) }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @PutMapping("/organization/{organization-id}/shop/{id}")
    @Transactional
    open fun editShop(
            @PathVariable("organization-id") organizationId: String,
            @PathVariable("id") id: String,
            @RequestBody form: ShopForm
    ): OrganizationDto {
        return repository.findById(organizationId).orElseThrow { IllegalArgumentException() }
                .apply {
                    val shop = shopConverter.toDomain(form).apply { this.id = id }
                    shops = shops.filter { it.id != id } + shop
                }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @DeleteMapping("/organization/{id}/shop/{shop-id}")
    @Transactional
    open fun removeShop(
            @PathVariable("id") id: String,
            @PathVariable("shop-id") shopId: String
    ): OrganizationDto {
        return repository.findById(id).orElseThrow { IllegalArgumentException() }
                .apply { shops = shops.filter { it.id != shopId } }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @PostMapping("/organization/{organization-id}/credit-offer")
    @Transactional
    open fun addCreditOffer(
            @PathVariable("organization-id") organizationId: String,
            @RequestBody form: CreditOfferForm
    ): OrganizationDto {
        return repository.findById(organizationId).orElseThrow { IllegalArgumentException() }
                .let { it as Country }
                .apply { creditOffers += creditOfferConverter.toDomain(form) }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @DeleteMapping("/organization/{organization-id}/credit-offer/{id}")
    @Transactional
    open fun removeCreditOffer(
            @PathVariable("organization-id") organizationId: String,
            @PathVariable("id") id: String
    ): OrganizationDto {
        return repository.findById(organizationId).orElseThrow { IllegalArgumentException() }
                .let { it as Country }
                .apply { creditOffers = creditOffers.filter { it.id != id } }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

}