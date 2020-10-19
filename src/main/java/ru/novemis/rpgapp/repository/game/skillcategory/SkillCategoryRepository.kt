package ru.novemis.rpgapp.repository.game.skillcategory

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.domain.game.skill.SkillCategory

interface SkillCategoryRepository : CrudRepository<SkillCategory, String> {

    @Query("select s from SkillCategory s where s.game.id = :gameId")
    fun findAllByGameId(gameId: String): List<SkillCategory>

    @Query("select s from SkillCategory s where s.game.id = :gameId and s.destination = :destination")
    fun findAllByGameIdAndDestination(gameId: String, destination: Destination): List<SkillCategory>
}