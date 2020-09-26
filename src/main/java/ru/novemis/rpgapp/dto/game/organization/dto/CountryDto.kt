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
        initialBudget: List<PriceDto>,

        val shops: List<ShopDto>
) : OrganizationDto(id, name, description, type, heads, initialBudget)