package ru.novemis.rpgapp.dto.game.pet.form

import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.dto.game.shop.form.SkillInfluenceForm

data class PetUpgradeForm(
        var lvl: Int = 0,
        var description: String? = null,
        var skillInfluences: List<SkillInfluenceForm> = emptyList(),
        var prices: List<List<PriceForm>> = emptyList()
)