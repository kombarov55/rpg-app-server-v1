package ru.novemis.rpgapp.dto.game.pet.dto

import ru.novemis.rpgapp.dto.game.common.dto.PriceDto
import ru.novemis.rpgapp.dto.game.shop.dto.SkillInfluenceDto

data class PetUpgradeDto(
        val id: String,
        val lvl: Int,
        val description: String?,
        val skillInfluences: List<SkillInfluenceDto>,
        val prices: List<List<PriceDto>>
)