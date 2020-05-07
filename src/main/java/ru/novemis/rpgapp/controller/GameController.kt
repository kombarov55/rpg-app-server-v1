package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.network.GameDto
import ru.novemis.rpgapp.dto.network.GameForm
import ru.novemis.rpgapp.service.GameService

@RestController
class GameController(
        private val gameService: GameService
) {

    @PostMapping("/game")
    fun save(@RequestBody form: GameForm): GameDto {
        return gameService.save(form)
    }

    @GetMapping("/network/{network-id}/game")
    fun findByNetworkId(@PathVariable("network-id") networkId: String): List<GameDto> {
        return gameService.findByNetworkId(networkId)
    }

    @GetMapping("/network/{network-id}/subnetwork/{subnetwork-id}/game")
    fun findBySubnetworkId(@PathVariable("subnetwork-id") subnetworkId: String): List<GameDto> {
        return gameService.findBySubnetworkId(subnetworkId)
    }

}