package ru.novemis.rpgapp.dto.game.skill.form

import ru.novemis.rpgapp.domain.game.shop.Destination


data class SkillCategoryForm(
        var name: String = "",
        var img: String = "",
        var description: String = "",
        var complex: Boolean = false,
        var skills: List<SkillForm>? = null,
        var spellSchools: List<SpellSchoolForm>? = null,
        var destination: Destination? = null
)