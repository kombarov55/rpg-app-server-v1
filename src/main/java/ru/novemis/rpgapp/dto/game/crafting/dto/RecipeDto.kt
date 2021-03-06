package ru.novemis.rpgapp.dto.game.crafting.dto

import ru.novemis.rpgapp.dto.game.shop.dto.ItemTemplateShortDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillShortDto

data class RecipeDto(
        val id: String,
        val name: String,
        val target: ItemTemplateShortDto,
        val ingredients: List<ItemTemplateAmountDto>,
        val dependantSkill: SkillShortDto,
        val minSkillLvl: Int,
        val successChanceDependencies: List<SuccessChanceDependencyDto>
)