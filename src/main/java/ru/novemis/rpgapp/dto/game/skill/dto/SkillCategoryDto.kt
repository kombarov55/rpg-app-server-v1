package ru.novemis.rpgapp.dto.game.skill.dto

import ru.novemis.rpgapp.domain.game.shop.Destination

data class SkillCategoryDto(
        val id: String,
        val img: String,
        val name: String,
        val description: String,
        val complex: Boolean,
        val skills: List<SkillDto>,
        val spellSchools: List<SpellSchoolDto>,
        val destination: Destination?,
        val gameId: String
)