package ru.novemis.rpgapp.dto.game.crafting.dto

import ru.novemis.rpgapp.domain.game.shop.Merchandise
import ru.novemis.rpgapp.dto.game.shop.dto.WarehouseEntryDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillShortDto

data class RecipeDto(
        val id: String,
        val target: Merchandise,
        val ingredients: List<WarehouseEntryDto>,
        val dependantSkill: SkillShortDto,
        val minSkillLevel: Int,
        val successChanceDependencies: List<SuccessChanceDependencyDto>
)