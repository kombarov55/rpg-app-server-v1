package ru.novemis.rpgapp.dto.game.crafting.form

data class SuccessChanceDependencyForm(
        val min: Int = 0,
        val max: Int = 0,
        val percent: Int = 0
)