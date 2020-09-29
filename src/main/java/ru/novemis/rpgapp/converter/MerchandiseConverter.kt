package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.Merchandise
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseCategoryDto
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseDto
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseShortDto
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseTypeDto
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.shop.MerchandiseCategoryRepository
import ru.novemis.rpgapp.repository.game.shop.MerchandiseTypeRepository

@Component
class MerchandiseConverter(
        private val merchandiseCategoryRepository: MerchandiseCategoryRepository,
        private val merchandiseTypeRepository: MerchandiseTypeRepository,
        private val skillInfluenceConverter: SkillInfluenceConverter,
        private val merchandiseUpgradeConverter: MerchandiseUpgradeConverter,
        private val gameRepository: GameRepository
) {

    fun toDomain(form: MerchandiseForm, gameId: String): Merchandise {
        return Merchandise(
                name = form.name,
                img = form.img,
                description = form.description,
                category = merchandiseCategoryRepository.findById(form.category!!.id!!).orElseThrow { IllegalArgumentException("categoryId is invalid") },
                type = merchandiseTypeRepository.findById(form.type!!.id!!).orElseThrow { IllegalArgumentException("typeId is invalid") },
                slots = form.slots,
                skillInfluences = form.skillInfluences.map { skillInfluenceConverter.toDomain(it) },
                game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException("gameId is invalid") },
                destination = form.destination,
                merchandiseUpgrades = form.merchandiseUpgrades.map { merchandiseUpgradeConverter.toDomain(it, gameId) }
        )
    }

    fun toDto(domain: Merchandise): MerchandiseDto {
        return MerchandiseDto(
                id = domain.id,
                name = domain.name,
                img = domain.img,
                description = domain.description ?: "",
                category = domain.category!!.let { MerchandiseCategoryDto(it.id, it.name) },
                type = domain.type!!.let { MerchandiseTypeDto(it.id, it.name) },
                slots = domain.slots,
                skillInfluences = domain.skillInfluences.map { skillInfluenceConverter.toDto(it) },
                destination = domain.destination!!,
                merchandiseUpgrades = domain.merchandiseUpgrades.map { merchandiseUpgradeConverter.toDto(it) }
        )
    }

    fun toShortDto(domain: Merchandise): MerchandiseShortDto {
        return MerchandiseShortDto(
                id = domain.id,
                name = domain.name,
                img = domain.img
        )
    }

}