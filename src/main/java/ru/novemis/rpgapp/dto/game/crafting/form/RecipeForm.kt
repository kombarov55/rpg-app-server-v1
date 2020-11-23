package ru.novemis.rpgapp.dto.game.crafting.form

import ru.novemis.rpgapp.dto.game.shop.form.ItemTemplateForm
import ru.novemis.rpgapp.dto.game.skill.form.SkillShortForm

data class RecipeForm(
        var target: ItemTemplateForm? = null,
        var ingredients: List<ItemTemplateForm> = mutableListOf(),
        var dependantSkill: SkillShortForm? = null,
        var minSkillLvl: Int = 0,
        var successChanceDependencies: List<SuccessChanceDependencyForm> = mutableListOf()
)