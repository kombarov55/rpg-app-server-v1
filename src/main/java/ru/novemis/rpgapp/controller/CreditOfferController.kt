package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.CreditOfferConverter
import ru.novemis.rpgapp.dto.game.organization.dto.CreditOfferDto
import ru.novemis.rpgapp.dto.game.organization.form.CreditOfferForm
import ru.novemis.rpgapp.repository.game.organization.CreditOfferRepository
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import javax.transaction.Transactional

@RestController
open class CreditOfferController(
        private val repository: CreditOfferRepository,
        private val converter: CreditOfferConverter,
        private val organizationRepository: OrganizationRepository
) {

    @PostMapping("/organization/{id}/credit-offer")
    @Transactional
    open fun save(
            @PathVariable("id") organizationId: String,
            @RequestBody form: CreditOfferForm
    ): CreditOfferDto {
        return converter.toDomain(form).apply {
            organization = organizationRepository.findById(organizationId).get()
        }.let { repository.save(it) }
         .let { converter.toDto(it) }
    }

    @DeleteMapping("/credit-offer/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ): CreditOfferDto {
        return repository.findById(id).get()
                .also { repository.delete(it) }
                .let { converter.toDto(it) }
    }

}