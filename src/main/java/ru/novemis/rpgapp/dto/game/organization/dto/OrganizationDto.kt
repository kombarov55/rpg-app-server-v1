package ru.novemis.rpgapp.dto.game.organization.dto

import ru.novemis.rpgapp.domain.game.organization.OrganizationType
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto
import ru.novemis.rpgapp.dto.game.common.dto.PriceDto
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.game.shop.dto.WarehouseEntryDto

open class OrganizationDto(
        val id: String,
        val name: String,
        val description: String,
        val type: OrganizationType,
        val heads: List<GameCharacterShortDto>,
        val balance: List<PriceDto>,
        val shops: List<ShopDto>,
        val ownedMerchandise: List<WarehouseEntryDto>,
        val entranceTax: List<PriceDto>? = emptyList(),
        val incomeTax: Double? = null,
        val creditOffers: List<CreditOfferDto>? = emptyList()
)