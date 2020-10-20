package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.questionnaire.Questionnaire
import ru.novemis.rpgapp.domain.game.questionnaire.SkillToLvl
import ru.novemis.rpgapp.dto.game.questionnaire.dto.SkillToLvlDto
import ru.novemis.rpgapp.dto.game.questionnaire.form.SkillToLvlForm
import ru.novemis.rpgapp.repository.game.skillcategory.SkillRepository

@Component
class SkillToLvlConverter(
        private val skillRepository: SkillRepository,
        private val skillConverter: SkillConverter
) {

    fun toDomain(form: SkillToLvlForm, questionnaire: Questionnaire? = null): SkillToLvl {
        return SkillToLvl(
                skill = skillRepository.findById(form.skill!!.id).get(),
                amount = form.amount,
                questionnaire = questionnaire
        )
    }

    fun toDto(domain: SkillToLvl): SkillToLvlDto {
        return SkillToLvlDto(
                id = domain.id,
                skill = skillConverter.toDto(domain.skill!!),
                amount = domain.amount
        )
    }

}