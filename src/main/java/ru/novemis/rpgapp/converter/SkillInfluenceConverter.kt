package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.SkillInfluence
import ru.novemis.rpgapp.dto.game.shop.dto.SkillInfluenceDto
import ru.novemis.rpgapp.dto.game.shop.form.SkillInfluenceForm
import ru.novemis.rpgapp.repository.game.skillcategory.SkillRepository

@Component
class SkillInfluenceConverter(
        private val skillRepository: SkillRepository,
        private val skillConverter: SkillConverter
) {

    fun toDomain(form: SkillInfluenceForm) = SkillInfluence(
            skill = skillRepository.findById(form.skill!!.id).orElseThrow { IllegalArgumentException("skillId is invalid") },
            modifier = form.modifier!!.toDomain(),
            amount = form.amount
    )

    fun toDto(domain: SkillInfluence) = SkillInfluenceDto(
            skill = skillConverter.toShortDto(domain.skill!!),
            modifier = domain.modifier!!.toDto(),
            amount = domain.amount
    )
}