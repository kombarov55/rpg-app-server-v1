package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.Item
import ru.novemis.rpgapp.dto.game.shop.dto.ItemDto

@Component
class ItemConverter(
        private val skillInfluenceConverter: SkillInfluenceConverter,
        private val itemUpgradeConverter: ItemUpgradeConverter
) {

    fun toDto(domain: Item): ItemDto {
        return ItemDto(
                id = domain.id,
                name = domain.itemTemplate!!.name,
                img = domain.itemTemplate.img,
                description = domain.itemTemplate.description ?: "",
                category = domain.itemTemplate.category!!.name,
                type = domain.itemTemplate.type!!.name,
                slots = domain.itemTemplate.slots,
                skillInfluences = domain.itemTemplate.skillInfluences.map { skillInfluenceConverter.toDto(it) },
                destination = domain.itemTemplate.destination!!,
                upgradable = domain.itemTemplate.upgradable,
                upgrades = domain.itemTemplate.upgrades.map { itemUpgradeConverter.toDto(it) },
                canBeEquipped = domain.itemTemplate.canBeEquipped,
                canBeUsedInCraft = domain.itemTemplate.canBeUsedInCraft,
                canBeCrafted = domain.itemTemplate.canBeCrafted,
                lvl = domain.lvl
        )
    }

}