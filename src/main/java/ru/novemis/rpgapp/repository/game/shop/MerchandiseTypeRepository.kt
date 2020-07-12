package ru.novemis.rpgapp.repository.game.shop

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.shop.MerchandiseType

interface MerchandiseTypeRepository : CrudRepository<MerchandiseType, String> {
}