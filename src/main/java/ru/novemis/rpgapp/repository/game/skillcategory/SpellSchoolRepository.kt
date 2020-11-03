package ru.novemis.rpgapp.repository.game.skillcategory

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.domain.game.skill.SpellSchool

interface SpellSchoolRepository : CrudRepository<SpellSchool, String> {

    fun findBySkillCategoryGameIdAndSkillCategoryDestination(gameId: String, destination: Destination): List<SpellSchool>

}