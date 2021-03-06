package ru.novemis.rpgapp.dto.game.organization.dto

import ru.novemis.rpgapp.domain.game.organization.OrganizationType

data class OrganizationShortDto(
        val id: String,
        val name: String,
        val description: String? = null,
        val type: OrganizationType? = null,
        val balanceId: String? = null
)