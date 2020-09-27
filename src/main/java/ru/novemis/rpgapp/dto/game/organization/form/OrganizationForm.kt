package ru.novemis.rpgapp.dto.game.organization.form

import ru.novemis.rpgapp.domain.game.organization.OrganizationTypeForm
import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseForm
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.dto.game.shop.form.WarehouseEntryForm
import ru.novemis.rpgapp.dto.useraccount.form.UserAccountShortForm

data class OrganizationForm(
        var id: String? = null,
        var name: String = "",
        var description: String = "",
        var type: OrganizationTypeForm? = null,
        var heads: List<UserAccountShortForm> = mutableListOf(),
        var balance: List<PriceForm> = mutableListOf(),
        var shops: List<ShopForm> = mutableListOf(),
        var ownedMerchandise: List<WarehouseEntryForm> = mutableListOf(),
        var entranceTax: List<PriceForm> = mutableListOf(),
        var incomeTax: Double = 0.0
)