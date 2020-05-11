package ru.novemis.rpgapp.repository.network

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.network.SkillType

interface SkillTypeRepository : CrudRepository<SkillType, String> {

    @Query("select st from SkillType st where st.game.id = :gameId and st.name = :name")
    fun findByGameIdAndName(gameId: String, name: String): SkillType?
}