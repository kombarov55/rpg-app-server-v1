package ru.novemis.rpgapp.repository.game.skill

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.skill.SkillType

interface SkillTypeRepository : CrudRepository<SkillType, String> {

    @Query("select st from SkillType st where st.game.id = :gameId and st.name = :name")
    fun findByGameIdAndName(gameId: String, name: String): SkillType?

    @Query("select st from SkillType st where st.game.id = :gameId")
    fun findByGameId(gameId: String): List<SkillType>
}