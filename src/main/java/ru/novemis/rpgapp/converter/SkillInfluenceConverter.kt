package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.SkillInfluence
import ru.novemis.rpgapp.dto.game.shop.dto.SkillInfluenceDto
import ru.novemis.rpgapp.dto.game.shop.form.SkillInfluenceForm
import ru.novemis.rpgapp.repository.game.skillcategory.SkillRepository

@Component
class SkillInfluenceConverter(
        private val skillRepository: SkillRepository
) {

    fun toDomain(form: SkillInfluenceForm) = SkillInfluence(
            skill = skillRepository.findById(form.skillId).orElseThrow { IllegalArgumentException("skillId is invalid") },
            modifier = form.modifier!!.toDomain(),
            amount = form.amount
    )

    fun toDto(domain: SkillInfluence) = SkillInfluenceDto(
            domain.skill!!.name,
            modifier = domain.modifier!!.toDto(),
            amount = domain.amount
    )
}