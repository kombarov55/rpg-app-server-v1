package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.questionnaire.Questionnaire
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import ru.novemis.rpgapp.dto.game.questionnaire.dto.QuestionnaireDto
import ru.novemis.rpgapp.dto.game.questionnaire.form.QuestionnaireForm
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SpellRepository

@Component
class QuestionnaireConverter(
        private val fieldToValueConverter: FieldToValueConverter,
        private val skillToLvlConverter: SkillToLvlConverter,
        private val spellRepository: SpellRepository,
        private val spellConverter: SpellConverter,
        private val organizationRepository: OrganizationRepository,
        private val organizationConverter: OrganizationConverter,
        private val userAccountConverter: UserAccountConverter
) {

    fun toDomain(form: QuestionnaireForm, game: Game? = null, author: UserAccount? = null): Questionnaire {
        return Questionnaire().apply {
            fieldToValueList = form.fieldToValueList.map { fieldToValueConverter.toDomain(it, this) }
            selectedSkillToLvlList = form.selectedSkillsToLvl.map { skillToLvlConverter.toDomain(it, this) }
            selectedSpells = form.selectedSpells.map { spellRepository.findById(it.id).get() }
            country = organizationRepository.findById(form.country!!.id!!).get()
            this.game = game
            this.author = author
        }
    }

    fun toDto(domain: Questionnaire): QuestionnaireDto {
        return QuestionnaireDto(
                id = domain.id,
                fieldToValueList = domain.fieldToValueList.map { fieldToValueConverter.toDto(it) },
                selectedSkillToLvlList = domain.selectedSkillToLvlList.map { skillToLvlConverter.toDto(it) },
                country = organizationConverter.toShortDto(domain.country!!),
                selectedSpells = domain.selectedSpells.map { spellConverter.toDto(it) },
                author = userAccountConverter.toShortDto(domain.author!!)
        )
    }

}