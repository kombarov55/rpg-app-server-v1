package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.CreditConverter
import ru.novemis.rpgapp.domain.game.organization.Credit
import ru.novemis.rpgapp.dto.game.organization.dto.CreditDto
import ru.novemis.rpgapp.dto.game.organization.dto.OverdueCreditDto
import ru.novemis.rpgapp.repository.game.organization.CreditRepository
import javax.transaction.Transactional

@RestController
open class CreditController(
        private val repository: CreditRepository,
        private val converter: CreditConverter
) {

    @GetMapping("/character/{id}/credit")
    @Transactional
    open fun findAllByCharacterId(@PathVariable("id") characterId: String): List<CreditDto> {
        return repository.findAllByOwnerId(characterId).map { converter.toDto(it) }
    }

    @GetMapping("/organization/{id}/credit/overdue")
    @Transactional
    open fun findAllOverdueByOrganizationId(@PathVariable("id") organizationId: String): List<OverdueCreditDto> {
        return repository.findAllByOrganizationIdAndIsOverdue(organizationId, true).map { converter.toOverdueDto(it) }
    }


}