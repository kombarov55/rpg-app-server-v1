package ru.novemis.rpgapp.dto.game.organization.dto

import ru.novemis.rpgapp.domain.game.organization.OrganizationType
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto
import ru.novemis.rpgapp.dto.game.character.dto.SkillStatsDto
import ru.novemis.rpgapp.dto.game.common.dto.PriceDto
import ru.novemis.rpgapp.dto.game.questionnaire.dto.SkillToLvlDto
import ru.novemis.rpgapp.dto.game.shop.dto.ItemDto
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.game.shop.dto.WarehouseEntryDto

open class OrganizationDto(
        val id: String,
        val name: String,
        val description: String,
        val type: OrganizationType,
        val skillStats: List<SkillStatsDto>,
        val heads: List<GameCharacterShortDto>,
        val balance: List<PriceDto>,
        val balanceId: String,
        val shops: List<ShopDto>,
        val items: List<ItemDto>,
        val incomeTax: Double? = null,
        val creditOffers: List<CreditOfferDto> = emptyList(),
        val equippedItems: List<ItemDto>
)