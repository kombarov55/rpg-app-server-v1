package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.common.TransferDestinationType
import ru.novemis.rpgapp.domain.game.shop.Item
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import ru.novemis.rpgapp.repository.game.shop.ItemRepository
import javax.transaction.Transactional

@Component
open class ItemService(
        private val organizationRepository: OrganizationRepository,
        private val characterRepository: GameCharacterRepository,
        private val itemRepository: ItemRepository
) {

    @Transactional
    open fun transferItem(itemId: String, fromType: TransferDestinationType, fromId: String, toType: TransferDestinationType, toId: String) {
        val item = itemRepository.findById(itemId).get()

        when (fromType) {
            TransferDestinationType.CHARACTER -> removeItemFromCharacter(fromId, item)
            TransferDestinationType.ORGANIZATION -> removeItemFromOrganization(fromId, item)
        }

        when (toType) {
            TransferDestinationType.CHARACTER -> addItemToCharacter(toId, item)
            TransferDestinationType.ORGANIZATION -> addItemToOrganization(toId, item)
        }
    }

    open fun removeItemFromCharacter(characterId: String, item: Item) {
        characterRepository.findById(characterId).get()
                .removeItem(item)
                .also { characterRepository.save(it) }
    }

    open fun addItemToCharacter(characterId: String, item: Item) {
        characterRepository.findById(characterId).get().addItem(item).also { characterRepository.save(it) }
    }

    open fun removeItemFromOrganization(organizationId: String, item: Item) {
        organizationRepository.findById(organizationId).get().apply {
            items = items.filter { it.id != item.id }
        }.also { organizationRepository.save(it) }
    }

    open fun addItemToOrganization(organizationId: String, item: Item) {
        organizationRepository.findById(organizationId).get().apply {
            items += item
        }.also { organizationRepository.save(it) }
    }

}