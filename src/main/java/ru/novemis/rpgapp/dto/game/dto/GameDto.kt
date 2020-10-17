package ru.novemis.rpgapp.dto.game.dto

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
        val maxCurrenciesCount: Int,
        val itemsForSale: List<ItemForSaleDto>,
        val disclaimerText: String?
)