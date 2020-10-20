package ru.novemis.rpgapp.repository.game.organization

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import ru.novemis.rpgapp.domain.game.organization.Organization
import ru.novemis.rpgapp.domain.game.organization.OrganizationType

interface OrganizationRepository : PagingAndSortingRepository<Organization, String> {

    fun findAllByGameId(gameId: String): List<Organization>

    fun findAllByGameIdAndType(gameId: String, type: OrganizationType): List<Organization>

    fun findByGameIdAndNameStartsWith(gameId: String, name: String, pageable: Pageable): List<Organization>
}