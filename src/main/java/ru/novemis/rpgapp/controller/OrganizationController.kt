package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.OrganizationConverter
import ru.novemis.rpgapp.converter.PriceCombinationConverter
import ru.novemis.rpgapp.converter.ShopConverter
import ru.novemis.rpgapp.domain.game.shop.WarehouseEntry
import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationDto
import ru.novemis.rpgapp.dto.game.organization.form.OrganizationForm
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import ru.novemis.rpgapp.repository.game.shop.MerchandiseRepository
import ru.novemis.rpgapp.repository.game.shop.ShopRepository
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import ru.novemis.rpgapp.service.CalcService
import javax.transaction.Transactional

@RestController
open class OrganizationController(
        private val repository: OrganizationRepository,
        private val converter: OrganizationConverter,

        private val gameRepository: GameRepository,
        private val userAccountRepository: UserAccountRepository,

        private val priceCombinationConverter: PriceCombinationConverter,

        private val calcService: CalcService,

        private val merchandiseRepository: MerchandiseRepository,

        private val shopRepository: ShopRepository,
        private val shopConverter: ShopConverter
) {

    @GetMapping("/game/{game-id}/organization")
    @Transactional
    open fun getAllByGameId(
            @PathVariable("game-id") gameId: String
    ): List<OrganizationDto> {
        return repository.findAllByGameId(gameId).map { converter.toDto(it) }
    }

    @PostMapping("/game/{game-id}/organization")
    @Transactional
    open fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody form: OrganizationForm
    ): OrganizationDto {
        return converter.toDomain(form, gameId)
                .apply { game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException() } }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @PutMapping("/game/{game-id}/organization/{id}")
    @Transactional
    open fun update(
            @PathVariable("game-id") gameId: String,
            @PathVariable("id") id: String,
            @RequestBody form: OrganizationForm
    ): OrganizationDto {
        return converter.toDomain(form, gameId)
                .apply {
                    this.id = id
                    this.game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException() }
                }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @PostMapping("/organization/{id}/head/{head-id}")
    @Transactional
    open fun addHead(
            @PathVariable("id") id: String,
            @PathVariable("head-id") headId: String
    ): OrganizationDto {
        return repository.findById(id).orElseThrow { IllegalArgumentException() }
                .apply { organizationHeads += userAccountRepository.findById(headId).orElseThrow { IllegalArgumentException() } }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @DeleteMapping("/organization/{id}/head/{head-id}")
    @Transactional
    open fun removeHead(
            @PathVariable("id") id: String,
            @PathVariable("head-id") headId: String
    ): OrganizationDto {
        return repository.findById(id).orElseThrow { IllegalArgumentException() }
                .apply { organizationHeads = organizationHeads.filter { it.id != headId } }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @DeleteMapping("/organization/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ): OrganizationDto {
        return repository.findById(id).orElseThrow { IllegalArgumentException() }
                .also { repository.deleteById(id) }
                .let { converter.toDto(it) }
    }

    @PostMapping("/organization/{id}/balance")
    @Transactional
    open fun addBalance(
            @PathVariable("id") id: String,
            @RequestBody amounts: List<PriceForm>
    ): OrganizationDto {
        return repository.findById(id).orElseThrow { IllegalArgumentException() }
                .apply {
                    val convertedFormAmounts = amounts.map { priceCombinationConverter.toDomain(it, this.game!!.id) }
                    balance = calcService.sum(balance, convertedFormAmounts)
                }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @PostMapping("/organization/{id}/ownedMerchandise/{merchandise-id}")
    @Transactional
    open fun addOwnedMerchandise(
            @PathVariable("id") id: String,
            @PathVariable("merchandise-id") merchandiseId: String,
            @RequestParam("amount") amount: Int
    ): OrganizationDto {
        val organization = repository.findById(id).orElseThrow { IllegalArgumentException() }
        val merchandise = merchandiseRepository.findById(merchandiseId).orElseThrow { IllegalArgumentException() }

        organization.ownedMerchandise += WarehouseEntry(
                merchandise = merchandise,
                amount = amount
        )

        return repository.save(organization)
                .let { converter.toDto(it) }
    }

    @PostMapping("/organization/{id}/shop")
    @Transactional
    open fun addShop(
            @PathVariable("id") id: String,
            @RequestBody form: ShopForm
    ): OrganizationDto {
        return repository.findById(id).orElseThrow { IllegalArgumentException() }
                .apply {
                    val organization = this
                    shops += shopConverter.toDomain(form).apply { this.organization = organization }
                }
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
        return repository.findById(id).get()?.apply {
            val organization = this

            val shopToDelete = organization.shops.find { shop ->
                shop.id == shopId
            }

            shopRepository.delete(shopToDelete)

            shops = shops.filter { it.id != shopId }
        }.let { converter.toDto(it) }
    }
}