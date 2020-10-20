package ru.novemis.rpgapp.dto.game.organization.form

import ru.novemis.rpgapp.domain.game.organization.OrganizationType

data class OrganizationForm(
        var id: String? = null,
        var name: String = "",
        var description: String = "",
        var type: OrganizationType = OrganizationType.COUNTRY
)