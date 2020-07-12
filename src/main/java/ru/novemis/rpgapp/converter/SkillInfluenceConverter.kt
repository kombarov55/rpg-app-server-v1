package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.SkillInfluence
import ru.novemis.rpgapp.dto.game.shop.form.SkillInfluenceForm
import ru.novemis.rpgapp.repository.game.skillcategory.SkillRepository

@Component
class SkillInfluenceConverter(
        private val skillRepository: SkillRepository
) {

    fun toDomain(form: SkillInfluenceForm): SkillInfluence {
        return SkillInfluence(
                skill = skillRepository.findById(form.skillId).orElseThrow { IllegalArgumentException("skillId is invalid") },
                modifier = form.modifier,
                amount = form.amount
        )
    }

}