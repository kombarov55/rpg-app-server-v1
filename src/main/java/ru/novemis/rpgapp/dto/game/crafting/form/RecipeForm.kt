package ru.novemis.rpgapp.dto.game.crafting.form

import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseForm
import ru.novemis.rpgapp.dto.game.shop.form.WarehouseEntryForm
import ru.novemis.rpgapp.dto.game.skill.form.SkillShortForm

data class RecipeForm(
        var target: MerchandiseForm? = null,
        var ingredients: List<WarehouseEntryForm> = mutableListOf(),
        var dependantSkill: SkillShortForm? = null,
        var minSkillLvl: Int = 0,
        var successChanceDependencies: List<SuccessChanceDependencyForm> = mutableListOf()
)