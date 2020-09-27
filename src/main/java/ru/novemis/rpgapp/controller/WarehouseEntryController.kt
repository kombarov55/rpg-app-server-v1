package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.WarehouseEntryConverter
import ru.novemis.rpgapp.dto.game.shop.dto.WarehouseEntryDto
import ru.novemis.rpgapp.dto.game.shop.form.WarehouseEntryForm
import ru.novemis.rpgapp.repository.game.shop.ShopRepository
import ru.novemis.rpgapp.repository.game.shop.WarehouseEntryRepository
import javax.transaction.Transactional

@RestController
open class WarehouseEntryController(
        private val repository: WarehouseEntryRepository,
        private val converter: WarehouseEntryConverter,

        private val shopRepository: ShopRepository
) {

    @PostMapping("/shop/{id}/warehouseEntry")
    fun save(
            @PathVariable("id") shopId: String,
            @RequestBody form: WarehouseEntryForm): WarehouseEntryDto {
        val warehouseEntry = converter.toDomain(form)

        shopRepository.findById(shopId).orElseThrow { IllegalArgumentException() }
                .apply { warehouseEntries += warehouseEntry }
                .let { shopRepository.save(it) }

        return converter.toDto(warehouseEntry)
    }

    @PutMapping("/shop/{shop-id}/warehouseEntry/{id}")
    fun update(
            @PathVariable("shop-id") shopId: String,
            @PathVariable("id") id: String,
            @RequestBody form: WarehouseEntryForm): WarehouseEntryDto {
        val warehouseEntry = converter.toDomain(form).apply { this.id = id }

        shopRepository.findById(shopId).orElseThrow { IllegalArgumentException() }
                .apply { warehouseEntries = warehouseEntries.filter { it.id != id } + warehouseEntry }
                .let { shopRepository.save(it) }

        return converter.toDto(warehouseEntry)
    }

    @DeleteMapping("/warehouseEntry/{id}")
    fun delete(
            @PathVariable("id") id: String
    ): WarehouseEntryDto {
        return repository.findById(id).orElseThrow { IllegalArgumentException() }
                .also { repository.delete(it) }
                .let { converter.toDto(it) }
    }

    @GetMapping("/shop/{id}/warehouseEntry")
    @Transactional
    open fun findByShopId(
            @PathVariable("id") shopId: String
    ): List<WarehouseEntryDto> {
        return shopRepository.findById(shopId)
                .map {shop ->
                    shop.warehouseEntries.map { warehouseEntry ->
                        converter.toDto(warehouseEntry)
                    }
                }.orElse(emptyList())

    }

}