package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.Merchandise
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseForm
import ru.novemis.rpgapp.repository.game.shop.MerchandiseCategoryRepository
import ru.novemis.rpgapp.repository.game.shop.MerchandiseTypeRepository

@Component
class MerchandiseConverter(
        private val merchandiseCategoryRepository: MerchandiseCategoryRepository,
        private val merchandiseTypeRepository: MerchandiseTypeRepository,
        private val priceCombinationConverter: PriceCombinationConverter,
        private val skillInfluenceConverter: SkillInfluenceConverter
) {

    fun toDomain(form: MerchandiseForm): Merchandise {
        return Merchandise(
                name = form.name,
                img = form.img,
                category = merchandiseCategoryRepository.findById(form.categoryId).orElseThrow { IllegalArgumentException("categoryId is invalid") },
                type = merchandiseTypeRepository.findById(form.typeId).orElseThrow { IllegalArgumentException("typeId is invalid") },
                slots = form.slots,
                price = form.prices.map { priceCombinationConverter.toDomain(it) },
                skillInfluences = form.skillInfluences.map { skillInfluenceConverter.toDomain(it) }
        )
    }

}