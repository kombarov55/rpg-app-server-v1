package ru.novemis.rpgapp.dto.game.crafting.dto

import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseShortDto
import ru.novemis.rpgapp.dto.game.shop.dto.WarehouseEntryDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillShortDto

data class RecipeDto(
        val id: String,
        val target: MerchandiseShortDto,
        val ingredients: List<WarehouseEntryDto>,
        val dependantSkill: SkillShortDto,
        val minSkillLvl: Int,
        val successChanceDependencies: List<SuccessChanceDependencyDto>
)