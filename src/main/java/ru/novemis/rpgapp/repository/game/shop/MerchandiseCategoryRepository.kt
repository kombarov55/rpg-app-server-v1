package ru.novemis.rpgapp.repository.game.shop

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.shop.MerchandiseCategory

interface MerchandiseCategoryRepository : CrudRepository<MerchandiseCategory, String> {
}