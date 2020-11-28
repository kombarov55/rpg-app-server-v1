package ru.novemis.rpgapp.dto.game.dto

import ru.novemis.rpgapp.dto.game.crafting.dto.RecipeDto
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationShortDto
import ru.novemis.rpgapp.dto.game.questionnaire_template.dto.QuestionnaireTemplateShortDto
import ru.novemis.rpgapp.dto.game.shop.dto.ItemForSaleDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryDto


data class GameDto(
        val id: String,
        val title: String,
        val description: String,
        val imgSrc: String,
        val backgroundImgSrc: String,
        val groupLink: String,
        val currencies: List<CurrencyDto>,
        val skillCategories: List<SkillCategoryDto>,
        val organizations: List<OrganizationShortDto>,
        val maxCurrenciesCount: Int,
        val itemsForSale: List<ItemForSaleDto>,
        val recipes: List<RecipeDto>,
        val questionnaireTemplates: List<QuestionnaireTemplateShortDto>,
        val settings: GameSettingsDto
)