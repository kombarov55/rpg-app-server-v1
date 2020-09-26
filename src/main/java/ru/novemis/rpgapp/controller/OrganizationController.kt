package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.OrganizationConverter
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationDto
import ru.novemis.rpgapp.dto.game.organization.form.OrganizationForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import javax.transaction.Transactional

@RestController
open class OrganizationController(
        private val repository: OrganizationRepository,
        private val converter: OrganizationConverter,

        private val gameRepository: GameRepository
) {

    @GetMapping("/game/{game-id}/organization")
    @Transactional
    open fun getAllByGameId(
            @PathVariable("game-id") gameId: String
    ): List<OrganizationDto> {
        return repository.findAllByGameId(gameId).map { converter.toDto(it) }
    }

    @PostMapping("/game/{game-id}/organization")
    @Transactional
    open fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody form: OrganizationForm
    ): OrganizationDto {
        return converter.toDomain(form)
                .apply { game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException() } }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @PutMapping("/game/{game-id}/organization/{id}")
    @Transactional
    open fun update(
            @PathVariable("game-id") gameId: String,
            @PathVariable("id") id: String,
            @RequestBody form: OrganizationForm
    ): OrganizationDto {
        return converter.toDomain(form)
                .apply {
                    this.id = id
                    this.game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException() }
                }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @DeleteMapping("/organization/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ): OrganizationDto {
        return repository.findById(id).orElseThrow { IllegalArgumentException() }
                .also { repository.deleteById(id) }
                .let { converter.toDto(it) }
    }
}