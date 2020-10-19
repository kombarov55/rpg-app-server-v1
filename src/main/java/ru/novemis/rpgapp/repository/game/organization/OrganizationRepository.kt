package ru.novemis.rpgapp.repository.game.organization

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.organization.Organization
import ru.novemis.rpgapp.domain.game.organization.OrganizationType

interface OrganizationRepository : CrudRepository<Organization, String> {

    fun findAllByGameId(gameId: String): List<Organization>

    fun findAllByGameIdAndType(gameId: String, type: OrganizationType): List<Organization>
}