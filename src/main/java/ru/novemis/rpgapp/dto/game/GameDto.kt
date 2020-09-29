package ru.novemis.rpgapp.dto.game

import ru.novemis.rpgapp.dto.game.shop.dto.ItemForSaleDto
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryDto


data class GameDto(
        var id: String,
        var title: String,
        var description: String,
        var imgSrc: String,
        var backgroundImgSrc: String,
        var groupLink: String,
        var currencies: List<CurrencyDto>,
        var skillCategories: List<SkillCategoryDto>,
        var maxCurrenciesCount: Int,
        var itemsForSale: List<ItemForSaleDto>
)