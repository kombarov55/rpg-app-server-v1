package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.domain.game.pet.PetTemplate
import ru.novemis.rpgapp.dto.game.pet.dto.PetTemplateDto
import ru.novemis.rpgapp.dto.game.pet.form.PetTemplateForm

@Component
class PetTemplateConverter(
        private val petUpgradeConverter: PetUpgradeConverter,
        private val priceCombinationConverter: PriceCombinationConverter
) {

    fun toDomain(form: PetTemplateForm, game: Game): PetTemplate {
        return PetTemplate().apply {
            val petTemplate = this

            this.game = game
            name = form.name
            img = form.img
            description = form.description
            upgrades = form.upgrades?.map { petUpgradeConverter.toDomain(it, game).apply { this.petTemplate = petTemplate } }?.toMutableList() ?: mutableListOf()
            prices = form.prices?.map { priceCombinationConverter.toDomain(it, game.id) } ?: mutableListOf()
        }
    }

    fun toDto(domain: PetTemplate): PetTemplateDto {
        return PetTemplateDto(
                id = domain.id,
                name = domain.name,
                description = domain.description,
                img = domain.img,
                upgrades = domain.upgrades.map { petUpgradeConverter.toDto(it) },
                prices = domain.prices.map { priceCombinationConverter.toDto(it) }
        )
    }

}