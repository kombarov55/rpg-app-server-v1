package ru.novemis.rpgapp.dto.game.organization.form

import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountDto

data class OrganizationForm(
        var id: String? = null,
        var name: String = "",
        var description: String = "",
        var type: String = "",
        var heads: List<UserAccountDto> = mutableListOf(),
        var initialBudget: List<PriceForm> = mutableListOf()
)