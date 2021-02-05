package ru.novemis.rpgapp.converter

import org.mapstruct.Mapper
import ru.novemis.rpgapp.domain.game.organization.Organization
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationShortDto

@Mapper
interface OrganizationConverterV2 {
    fun toShortDto(domain: Organization): OrganizationShortDto
}