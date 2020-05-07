package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.network.NetworkDto
import ru.novemis.rpgapp.dto.network.NetworkForm
import ru.novemis.rpgapp.service.NetworkService

@RestController
@RequestMapping("/network")
class NetworkController(
        private val networkService: NetworkService
) {

    @PostMapping
    fun save(@RequestBody form: NetworkForm): NetworkDto {
        return networkService.save(form)
    }

    @GetMapping
    fun getAll(): List<NetworkDto> {
        return networkService.getAll()
    }

    @DeleteMapping("/{network-id}")
    fun delete(@PathVariable("network-id") networkId: String) {
        networkService.delete(networkId)
    }

}