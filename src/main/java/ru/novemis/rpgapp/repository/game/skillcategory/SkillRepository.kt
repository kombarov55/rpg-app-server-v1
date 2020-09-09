package ru.novemis.rpgapp.repository.game.skillcategory

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.skill.Skill

interface SkillRepository : CrudRepository<Skill, String> {

    @Query("select s from Skill s where s.skillCategory.game.id = :gameId")
    fun findByGameId(gameId: String): List<Skill>

    fun findBySkillCategoryId(skillCategoryId: String): List<Skill>

}