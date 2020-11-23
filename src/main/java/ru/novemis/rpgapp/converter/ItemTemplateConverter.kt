package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.ItemTemplate
import ru.novemis.rpgapp.dto.game.shop.dto.*
import ru.novemis.rpgapp.dto.game.shop.form.ItemTemplateForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.shop.ItemCategoryRepository
import ru.novemis.rpgapp.repository.game.shop.ItemTypeRepository

@Component
class ItemTemplateConverter(
        private val itemCategoryRepository: ItemCategoryRepository,
        private val itemTypeRepository: ItemTypeRepository,
        private val skillInfluenceConverter: SkillInfluenceConverter,
        private val itemUpgradeConverter: ItemUpgradeConverter,
        private val gameRepository: GameRepository
) {

    fun toDomain(form: ItemTemplateForm, gameId: String): ItemTemplate {
        return ItemTemplate(
                name = form.name,
                img = form.img,
                description = form.description,
                category = itemCategoryRepository.findById(form.category!!.id!!).orElseThrow { IllegalArgumentException("categoryId is invalid") },
                type = itemTypeRepository.findById(form.type!!.id!!).orElseThrow { IllegalArgumentException("typeId is invalid") },
                slots = form.slots,
                skillInfluences = form.skillInfluences.map { skillInfluenceConverter.toDomain(it) },
                game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException("gameId is invalid") },
                destination = form.destination,
                upgradable = form.upgradable,
                upgrades = form.upgrades.map { itemUpgradeConverter.toDomain(it, gameId) },
                canBeEquipped = form.canBeEquipped,
                canBeCrafted = form.canBeCrafted,
                canBeUsedInCraft = form.canBeUsedInCraft
        )
    }

    fun toDto(domain: ItemTemplate): ItemTemplateDto {
        return ItemTemplateDto(
                id = domain.id,
                name = domain.name,
                img = domain.img,
                description = domain.description ?: "",
                category = domain.category!!.let { ItemCategoryDto(it.id, it.name) },
                type = domain.type!!.let { ItemTypeDto(it.id, it.name) },
                slots = domain.slots,
                skillInfluences = domain.skillInfluences.map { skillInfluenceConverter.toDto(it) },
                destination = domain.destination!!,
                upgradable = domain.upgradable,
                upgrades = domain.upgrades.map { itemUpgradeConverter.toDto(it) },
                canBeEquipped = domain.canBeEquipped,
                canBeUsedInCraft = domain.canBeUsedInCraft,
                canBeCrafted = domain.canBeCrafted
        )
    }

    fun toShortDto(domain: ItemTemplate): ItemTemplateShortDto {
        return ItemTemplateShortDto(
                id = domain.id,
                name = domain.name,
                img = domain.img,
                description = domain.description
        )
    }

}