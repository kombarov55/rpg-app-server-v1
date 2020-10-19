package ru.novemis.rpgapp.service

import org.springframework.stereotype.Service
import ru.novemis.rpgapp.converter.SkillCategoryConverter
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryShortDto
import ru.novemis.rpgapp.dto.game.skill.form.SkillCategoryForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SkillCategoryRepository
import javax.transaction.Transactional

@Service
open class SkillCategoryService(
        private val repository: SkillCategoryRepository,
        private val converter: SkillCategoryConverter,

        private val gameRepository: GameRepository
) {

    @Transactional
    open fun findAllByGameId(gameId: String): List<SkillCategoryDto> =
            repository
                    .findAllByGameId(gameId)
                    .map { converter.toDto(it) }

    @Transactional
    open fun findAllShortByGameId(gameId: String): List<SkillCategoryShortDto> =
            repository
                    .findAllByGameId(gameId)
                    .map { converter.toShortDto(it) }

    @Transactional
    open fun findAllByGameIdAndDestination(gameId: String, destination: Destination): List<SkillCategoryDto> {
        return repository.findAllByGameIdAndDestination(gameId, destination)
                .map { converter.toDto(it) }
    }

    open fun save(skillCategoryForm: SkillCategoryForm, gameId: String): SkillCategoryDto =
            skillCategoryForm
                    .let { converter.toDomain(it).apply { game = gameRepository.findById(gameId).get() } }
                    .let { repository.save(it) }
                    .let { converter.toDto(it) }

    @Transactional
    open fun findById(id: String): SkillCategoryDto =
            id
                    .let { repository.findById(it) }
                    .map { converter.toDto(it) }
                    .orElseThrow { IllegalArgumentException("skillcategory $id not found") }

    @Transactional
    open fun update(id: String, body: SkillCategoryForm): SkillCategoryDto =
            repository.findById(id)
                    .orElseThrow { IllegalArgumentException("skillCategory $id not found") }
                    .apply {
                        name = body.name
                        img = body.img
                        description = body.description
                    }
                    .let { repository.save(it) }
                    .let { converter.toDto(it) }


    @Transactional
    open fun deleteById(id: String): SkillCategoryDto {
        val skillCategory = repository.findById(id).orElseThrow { IllegalArgumentException() }

        return converter.toDto(skillCategory)
                .also { repository.deleteById(id) }
    }

}