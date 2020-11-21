package ru.novemis.rpgapp.repository.game.organization

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.organization.CreditRequest
import ru.novemis.rpgapp.domain.game.organization.CreditRequestStatus

interface CreditRequestRepository : CrudRepository<CreditRequest, String> {
    fun findAllByStatusAndOrganizationId(status: CreditRequestStatus, organizationId: String): List<CreditRequest>
}