package ru.novemis.rpgapp.repository.game.skillcategory

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.domain.game.skill.Skill

interface SkillRepository : CrudRepository<Skill, String> {
    @Query("select s from Skill s where s.skillCategory.game.id = :gameId")
    fun findByGameId(gameId: String): List<Skill>
    fun findBySkillCategoryId(skillCategoryId: String): List<Skill>
    @Query("select T from Skill T where T.skillCategory.game.id = :gameId and T.skillCategory.destination = :destination")
    fun findByGameIdAndDestination(gameId: String, destination: Destination): List<Skill>
    fun findBySkillCategoryGameIdAndNameStartingWith(gameId: String, name: String): List<Skill>
}