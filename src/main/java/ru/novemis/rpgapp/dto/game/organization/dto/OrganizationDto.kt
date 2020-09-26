package ru.novemis.rpgapp.dto.game.organization.dto

import ru.novemis.rpgapp.domain.game.organization.OrganizationTypeDto
import ru.novemis.rpgapp.dto.game.common.dto.PriceDto
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountShortDto

open class OrganizationDto(
        val id: String,
        val name: String,
        val description: String,
        val type: OrganizationTypeDto,
        val heads: List<UserAccountShortDto>,
        val initialBudget: List<PriceDto>,
        val shops: List<ShopDto>
)