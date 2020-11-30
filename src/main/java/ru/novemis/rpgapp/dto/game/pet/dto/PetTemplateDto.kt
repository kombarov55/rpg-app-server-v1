package ru.novemis.rpgapp.dto.game.pet.dto

import ru.novemis.rpgapp.dto.game.common.dto.PriceDto

data class PetTemplateDto(
        val id: String,
        val name: String,
        val description: String?,
        val img: String,
        val upgrades: List<PetUpgradeDto>?,
        val prices: List<List<PriceDto>>?
)