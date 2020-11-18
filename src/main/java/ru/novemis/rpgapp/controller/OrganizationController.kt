package ru.novemis.rpgapp.controller

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.CreditOfferConverter
import ru.novemis.rpgapp.converter.OrganizationConverter
import ru.novemis.rpgapp.converter.ShopConverter
import ru.novemis.rpgapp.domain.game.organization.OrganizationType
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationDto
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationShortDto
import ru.novemis.rpgapp.dto.game.organization.form.CreditOfferForm
import ru.novemis.rpgapp.dto.game.organization.form.OrganizationForm
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import ru.novemis.rpgapp.repository.game.shop.ItemTemplateRepository
import ru.novemis.rpgapp.repository.game.shop.ShopRepository
import javax.transaction.Transactional

@RestController
open class OrganizationController(
        private val repository: OrganizationRepository,
        private val converter: OrganizationConverter,

        private val gameRepository: GameRepository,
        private val gameCharacterRepository: GameCharacterRepository,

        private val shopRepository: ShopRepository,
        private val shopConverter: ShopConverter,

        private val creditOfferConverter: CreditOfferConverter
) {

    @GetMapping("/organization/{id}")
    @Transactional
    open fun getById(@PathVariable("id") id: String): OrganizationDto {
        return repository.findById(id).get().let { converter.toDto(it) }
    }

    @GetMapping("/game/{game-id}/organization")
    @Transactional
    open fun getAllByGameId(
            @PathVariable("game-id") gameId: String
    ): List<OrganizationDto> {
        return repository.findAllByGameId(gameId).map { converter.toDto(it) }
    }

    @GetMapping("/game/{game-id}/organization/short")
    @Transactional
    open fun getAllByGameIdShort(
            @PathVariable("game-id") gameId: String
    ): List<OrganizationShortDto> {
        return repository.findAllByGameId(gameId).map { converter.toShortDto(it) }
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
                .apply { heads += gameCharacterRepository.findById(headId).get() }
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
                .apply { heads = heads.filter { it.id != headId } }
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
                    val organization = this

                    val shop = shopConverter.toDomain(form).apply {
                        this.id = id
                        this.organization = organization
                    }
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

    @GetMapping("/game/{id}/organization/filter")
    @Transactional
    open fun findByGameIdAndType(
            @PathVariable("id") gameId: String,
            @RequestParam("type") type: String
    ): List<OrganizationDto> {
        return repository.findAllByGameIdAndType(gameId, OrganizationType.valueOf(type))
                .map { converter.toDto(it) }
    }

    @GetMapping("/game/{id}/organization/filterByName")
    @Transactional
    open fun findCharacterByName(
            @PathVariable("id") gameId: String,
            @RequestParam("name") name: String
    ): List<OrganizationShortDto> {
        return repository.findByGameIdAndNameStartsWith(gameId, name, PageRequest.of(0, 5)).map { organization ->
            OrganizationShortDto(
                    id = organization.id,
                    name = organization.name,
                    balanceId = organization.balance!!.id
            )
        }
    }

    data class OrganizationPatch(
            val incomeTax: Double? = null
    )

    @PatchMapping("/organization/{id}")
    @Transactional
    open fun patch(
            @PathVariable("id") id: String,
            @RequestBody patch: OrganizationPatch): OrganizationDto {
        return repository.findById(id).get()
                .applyPatch(patch)
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @PostMapping("/organization/{id}/credit-offer")
    @Transactional
    open fun saveCreditOffer(
            @PathVariable("id") organizationId: String,
            @RequestBody form: CreditOfferForm
    ): OrganizationDto {
        return repository.findById(organizationId).get().apply {
            creditOffers += creditOfferConverter.toDomain(form)
        }.let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @PutMapping("/organization/{organization-id}/credit-offer/{id}")
    @Transactional
    open fun saveCreditOffer(
            @PathVariable("organization-id") organizationId: String,
            @PathVariable("id") creditOfferId: String,
            @RequestBody form: CreditOfferForm
    ): OrganizationDto {
        return repository.findById(organizationId).get().apply {
            creditOffers = creditOffers.filter { it.id != creditOfferId }
            creditOffers += creditOfferConverter.toDomain(form).apply { id = creditOfferId }
        }.let { repository.save(it) }
         .let { converter.toDto(it) }
    }

    @DeleteMapping("/organization/{organization-id}/credit-offer/{id}")
    @Transactional
    open fun deleteCreditOffer(
            @PathVariable("organization-id") organizationId: String,
            @PathVariable("id") creditOfferId: String
    ): OrganizationDto {
        return repository.findById(organizationId).get().apply {
            creditOffers = creditOffers.filter { it.id != creditOfferId }
        }.let { repository.save(it) }
         .let { converter.toDto(it) }
    }
}