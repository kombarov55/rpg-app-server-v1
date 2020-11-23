package ru.novemis.rpgapp.dto.game.skill.dto

import ru.novemis.rpgapp.dto.game.common.dto.PriceDto

data class SpellDto(
        val id: String,
        val img: String = "",
        val name: String,
        val description: String = "",
        var spellSchoolName: String? = null,
        var lvl: Int? = null,
        var prices: List<List<PriceDto>>? = null,
        var requiredSpells: List<SpellDto> = emptyList()
)