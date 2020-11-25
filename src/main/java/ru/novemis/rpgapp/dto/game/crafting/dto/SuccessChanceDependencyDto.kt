package ru.novemis.rpgapp.dto.game.crafting.dto

data class SuccessChanceDependencyDto(
        val id: String,
        val min: Int,
        val max: Int?,
        val percent: Int
)