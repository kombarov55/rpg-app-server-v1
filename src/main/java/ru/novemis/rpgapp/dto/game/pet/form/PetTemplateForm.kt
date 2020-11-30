package ru.novemis.rpgapp.dto.game.pet.form

import ru.novemis.rpgapp.dto.game.common.form.PriceForm

data class PetTemplateForm(
        var name: String = "",
        var img: String = "",
        var description: String? = "",
        var upgrades: List<PetUpgradeForm>? = emptyList(),
        var prices: List<List<PriceForm>>? = emptyList()
)