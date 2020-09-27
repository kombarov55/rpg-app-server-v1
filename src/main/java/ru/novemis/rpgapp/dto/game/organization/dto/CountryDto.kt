package ru.novemis.rpgapp.dto.game.organization.dto

import ru.novemis.rpgapp.domain.game.organization.OrganizationTypeDto
import ru.novemis.rpgapp.dto.game.common.dto.PriceDto
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountShortDto

class CountryDto(
        id: String,
        name: String,
        description: String,
        type: OrganizationTypeDto,
        heads: List<UserAccountShortDto>,
        balance: List<PriceDto>,
        shops: List<ShopDto>,

        val entranceTax: List<PriceDto>,
        val incomeTax: Double?,
        val creditOffers: List<CreditOfferDto>
) : OrganizationDto(id, name, description, type, heads, balance, shops)