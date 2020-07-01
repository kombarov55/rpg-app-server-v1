package ru.novemis.rpgapp.dto.game.skill.form

import ru.novemis.rpgapp.dto.game.common.form.PriceForm

data class SkillForm(
        var name: String = "",
        var img: String = "",
        var description: String = "",
        var prices: List<List<PriceForm>> = mutableListOf(),
        var skillUpgrades: List<SkillUpgradeForm> = mutableListOf()
)