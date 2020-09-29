package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.ItemForSaleConverter
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.dto.game.shop.dto.ItemForSaleDto
import ru.novemis.rpgapp.repository.game.shop.ItemForSaleRepository
import javax.transaction.Transactional

@RestController
open class ItemForSaleController(
        private val repository: ItemForSaleRepository,
        private val converter: ItemForSaleConverter
) {

    @GetMapping("/game/{game-id}/itemsForSale/filter")
    @Transactional
    open fun findItemsForSale(
            @PathVariable("game-id") gameId: String,
            @RequestParam("destination") destination: String
    ): List<ItemForSaleDto> {
        return repository.findByGameIdAndDesination(gameId, Destination.valueOf(destination))
                .map { converter.toDto(it) }
    }

}