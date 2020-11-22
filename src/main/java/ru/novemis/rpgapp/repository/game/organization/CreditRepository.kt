package ru.novemis.rpgapp.repository.game.organization

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.organization.Credit

interface CreditRepository : CrudRepository<Credit, String> {
    fun findAllByOwnerId(characterId: String): List<Credit>

    fun findAllByOrganizationIdAndIsOverdue(organizationId: String, isOverdue: Boolean): List<Credit>
}