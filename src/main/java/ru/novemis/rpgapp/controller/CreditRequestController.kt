package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.CreditRequestConverter
import ru.novemis.rpgapp.domain.game.organization.CreditRequestStatus
import ru.novemis.rpgapp.dto.game.organization.form.CreditRequestDto
import ru.novemis.rpgapp.repository.game.organization.CreditRequestRepository
import javax.transaction.Transactional

@RestController
open class CreditRequestController(
        private val repository: CreditRequestRepository,
        private val converter: CreditRequestConverter
) {
    @GetMapping("/organization/{id}/credit-request/pending")
    @Transactional
    open fun findAllByGameId(
            @PathVariable("id") organizationId: String
    ): List<CreditRequestDto> {
        return repository.findAllByStatusAndOrganizationId(CreditRequestStatus.PENDING, organizationId)
                .map { converter.toDto(it) }
    }

    @DeleteMapping("/credit-request/{id}")
    open fun delete(
            @PathVariable("id") id: String
    ): CreditRequestDto {
        val creditRequest = repository.findById(id).get()
        if (creditRequest.status === CreditRequestStatus.PENDING) {
            repository.delete(creditRequest)
            return converter.toDto(creditRequest)
        } else {
            throw RuntimeException("Нельзя удалить действующий кредит")
        }
    }

}