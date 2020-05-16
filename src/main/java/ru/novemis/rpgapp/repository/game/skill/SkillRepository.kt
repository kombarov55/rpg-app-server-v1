package ru.novemis.rpgapp.repository.game.skill

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.skill.Skill

interface SkillRepository : CrudRepository<Skill, String> {

    fun findByGameId(gameId: String): List<Skill>

}