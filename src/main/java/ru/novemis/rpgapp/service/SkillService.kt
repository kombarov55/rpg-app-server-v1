package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.SkillConverter
import ru.novemis.rpgapp.dto.game.skill.SkillForm
import ru.novemis.rpgapp.dto.game.skill.SkillDto
import ru.novemis.rpgapp.repository.game.skill.SkillRepository
import javax.transaction.Transactional

@Component
open class SkillService(
        private val skillConverter: SkillConverter,
        private val skillRepository: SkillRepository
) {

    @Transactional
    open fun findByGameId(gameId: String): List<SkillDto> {
        return skillRepository.findByGameId(gameId).map { skillConverter.toDto(it) }
    }

    @Transactional
    open fun save(form: SkillForm): SkillDto {
        return skillConverter.toDomain(form)
                .let { skillRepository.save(it) }
                .let { skillConverter.toDto(it) }
    }

    @Transactional
    open fun update(skillId: String, form: SkillForm): SkillDto {
        return skillConverter.toDomain(form)
                .apply { id = skillId }
                .let { skillRepository.save(it) }
                .let { skillConverter.toDto(it) }
    }

}