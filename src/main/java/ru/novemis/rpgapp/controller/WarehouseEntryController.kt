package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.WarehouseEntryConverter
import ru.novemis.rpgapp.dto.game.shop.dto.WarehouseEntryDto
import ru.novemis.rpgapp.dto.game.shop.form.WarehouseEntryForm
import ru.novemis.rpgapp.repository.game.shop.WarehouseEntryRepository

@RestController
class WarehouseEntryController(
        private val repository: WarehouseEntryRepository,
        private val converter: WarehouseEntryConverter
) {

    @PostMapping("/shop/{id}/warehouseEntry")
    fun save(
            @PathVariable("id") shopId: String,
            @RequestBody form: WarehouseEntryForm): WarehouseEntryDto {
        return converter.toDomain(form, shopId)
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @PutMapping("/shop/{shop-id}/warehouseEntry/{id}")
    fun update(
            @PathVariable("shop-id") shopId: String,
            @PathVariable("id") id: String,
            @RequestBody form: WarehouseEntryForm): WarehouseEntryDto {
        return converter.toDomain(form, shopId)
                .apply { this.id = id }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
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
    fun findByShopId(
            @PathVariable("id") shopId: String
    ): List<WarehouseEntryDto> {
        return repository.findAllByShopId(shopId).map { converter.toDto(it) }
    }

}