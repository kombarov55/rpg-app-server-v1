package ru.novemis.rpgapp.repository.game.pet

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.pet.PetTemplate

interface PetTemplateRepository : CrudRepository<PetTemplate, String> {
    fun findByGameId(gameId: String): List<PetTemplate>
}