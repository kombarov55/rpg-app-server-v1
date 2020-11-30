package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.GameSettings
import ru.novemis.rpgapp.dto.game.dto.GameDto
import ru.novemis.rpgapp.dto.game.dto.GameShortDto
import ru.novemis.rpgapp.dto.game.form.GameForm
import ru.novemis.rpgapp.repository.network.NetworkRepository
import ru.novemis.rpgapp.repository.network.SubnetworkRepository
import ru.novemis.rpgapp.util.appendProtocol
import java.util.*

@Component
class GameConverter(
        private val networkRepository: NetworkRepository,
        private val subnetworkRepository: SubnetworkRepository,
        private val currencyConverter: CurrencyConverter,
        private val skillCategoryConverter: SkillCategoryConverter,
        private val itemForSaleConverter: ItemForSaleConverter,
        private val organizationConverter: OrganizationConverter,
        private val recipeConverter: RecipeConverter,
        private val questionnaireTemplateConverter: QuestionnaireTemplateConverter,
        private val gameSettingsConverter: GameSettingsConverter,
        private val petTemplateConverter: PetTemplateConverter
) {

    fun toDomain(form: GameForm, gameId: String? = null, networkId: String? = null, subnetworkId: String? = null): Game {
        return Game().apply {
            val game = this

            id = gameId ?: UUID.randomUUID().toString()
            title = form.title
            description = form.description
            imgName = form.img
            backgroundName = form.background
            groupLink = appendProtocol(form.groupLink)
            network = networkId?.let { networkRepository.findById(it) }?.orElseThrow { IllegalArgumentException() }
            subnetwork = subnetworkId?.let { subnetworkRepository.findById(it) }?.orElseThrow { IllegalArgumentException() }
            currencies = form.currencies.map { currencyForm -> currencyConverter.toDomainOrExisting(game, currencyForm) }
            settings = GameSettings(game = game)
        }
    }

    fun toDto(domain: Game): GameDto {
        return GameDto(
                id = domain.id,
                title = domain.title,
                description = domain.description,
                imgSrc = domain.imgName,
                backgroundImgSrc = domain.backgroundName,
                groupLink = domain.groupLink,
                currencies = domain.currencies.map { currency -> currencyConverter.toDto(currency) },
                skillCategories = domain.skillCategories.map { skillCategoryConverter.toDto(it) },
                maxCurrenciesCount = 3,
                itemsForSale = domain.itemsForSale.map { itemForSaleConverter.toDto(it) },
                organizations = domain.organizations.map { organizationConverter.toShortDto(it) },
                recipes = domain.recipes.map { recipeConverter.toDto(it) },
                questionnaireTemplates = domain.questionnaireTemplates.map { questionnaireTemplateConverter.toShortDto(it) },
                settings = gameSettingsConverter.toDto(domain.settings),
                petTemplates = domain.petTemplates.map { petTemplateConverter.toDto(it) }

        )
    }

    fun toShortDto(domain: Game): GameShortDto {
        return GameShortDto(
                id = domain.id,
                name = domain.title,
                img = domain.imgName
        )
    }

}