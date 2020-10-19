package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.questionnaire.Questionnaire
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import ru.novemis.rpgapp.dto.game.questionnaire.form.QuestionnaireForm
import ru.novemis.rpgapp.repository.game.GameRepository

@Component
class QuestionnaireConverter(
        private val fieldToValueConverter: FieldToValueConverter,
        private val gameRepository: GameRepository
) {

    fun toDomain(form: QuestionnaireForm, game: Game? = null, userAccount: UserAccount? = null): Questionnaire {
        return Questionnaire().apply {
            fieldToValueList = form.fieldToValueList.map { fieldToValueConverter.toDomain(it, this) }
            this.game = game
            this.userAccount = userAccount
        }
    }

    fun toDto() {

    }

}