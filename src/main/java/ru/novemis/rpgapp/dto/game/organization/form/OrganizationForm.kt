package ru.novemis.rpgapp.dto.game.organization.form

import ru.novemis.rpgapp.domain.game.organization.OrganizationTypeForm
import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.dto.useraccount.form.UserAccountShortForm

data class OrganizationForm(
        var id: String? = null,
        var name: String = "",
        var description: String = "",
        var type: OrganizationTypeForm? = null,
        var heads: List<UserAccountShortForm> = mutableListOf(),
        var initialBudget: List<PriceForm> = mutableListOf()
)