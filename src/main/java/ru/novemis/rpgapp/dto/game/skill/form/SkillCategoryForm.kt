package ru.novemis.rpgapp.dto.game.skill.form

data class SkillCategoryForm(
        var name: String = "",
        var img: String = "",
        var description: String = "",
        var complex: Boolean = false,
        var skills: List<SkillForm>? = null,
        var spellSchools: List<SpellSchoolForm>? = null

)