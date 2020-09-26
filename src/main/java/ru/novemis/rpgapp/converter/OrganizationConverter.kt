package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.organization.Organization
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationDto
import ru.novemis.rpgapp.dto.game.organization.form.OrganizationForm
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository

@Component
class OrganizationConverter(
        private val userAccountRepository: UserAccountRepository,
        private val userAccountConverter: UserAccountConverter,
        private val priceCombinationConverter: PriceCombinationConverter
) {

    fun toDomain(form: OrganizationForm, gameId: String? = null): Organization {
        return Organization(
                name = form.name,
                description = form.description,
                type = form.type!!.toDomain(),
                organizationHeads = form.heads.map { userAccountRepository.findById(it.id).get() },
                initialBudget = gameId?.let { gameId ->
                    form.initialBudget.map { price ->
                        priceCombinationConverter.toDomain(price, gameId)
                    }
                } ?: emptyList()
        )
    }

    fun toDto(domain: Organization): OrganizationDto {
        return OrganizationDto(
                id = domain.id,
                name = domain.name,
                description = domain.description,
                type = domain.type!!.toDto(),
                heads = domain.organizationHeads.map { userAccountConverter.toShortDto(it) },
                initialBudget = domain.initialBudget.map { priceCombinationConverter.toDto(it) }
        )
    }

}