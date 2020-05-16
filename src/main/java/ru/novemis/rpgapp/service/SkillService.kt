package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.SkillConverter
import ru.novemis.rpgapp.dto.game.SkillForm
import ru.novemis.rpgapp.dto.game.skill.SkillShortDto
import ru.novemis.rpgapp.repository.game.skill.SkillRepository
import javax.transaction.Transactional

@Component
open class SkillService(
        private val skillConverter: SkillConverter,
        private val skillRepository: SkillRepository
) {

    @Transactional
    open fun findByGameId(gameId: String): List<SkillShortDto> {
        return skillRepository.findByGameId(gameId).map { skillConverter.toDto(it) }
    }

    @Transactional
    open fun save(form: SkillForm): SkillShortDto {
        return skillConverter.toDomain(form)
                .let { skillRepository.save(it) }
                .let { skillConverter.toDto(it) }
    }

}