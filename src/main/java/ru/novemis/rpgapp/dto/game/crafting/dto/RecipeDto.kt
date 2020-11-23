package ru.novemis.rpgapp.dto.game.crafting.dto

import ru.novemis.rpgapp.dto.game.shop.dto.ItemTemplateDto
import ru.novemis.rpgapp.dto.game.shop.dto.ItemTemplateShortDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillShortDto

data class RecipeDto(
        val id: String,
        val target: ItemTemplateDto,
        val ingredients: List<ItemTemplateShortDto>,
        val dependantSkill: SkillShortDto,
        val minSkillLvl: Int,
        val successChanceDependencies: List<SuccessChanceDependencyDto>
)