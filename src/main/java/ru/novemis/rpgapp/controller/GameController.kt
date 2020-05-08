package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.network.GameDto
import ru.novemis.rpgapp.dto.network.GameForm
import ru.novemis.rpgapp.service.GameService

@RestController
class GameController(
        private val gameService: GameService
) {

    @PostMapping("/network/{network-id}/game")
    fun saveByNetwork(@PathVariable("network-id") networkId: String,
                      @RequestBody form: GameForm): GameDto {
        form.networkId = networkId
        return gameService.save(form)
    }

    @PostMapping("/network/{network-id}/subnetwork/{subnetwork-id}/game")
    fun saveBySubnetwork(@PathVariable("subnetwork-id") subnetworkId: String,
                         @RequestBody form: GameForm): GameDto {
        form.subnetworkId = subnetworkId
        return gameService.save(form)
    }

    @PutMapping("/network/{network-id}/game/{game-id}")
    fun updateByNetworkId(@PathVariable("network-id") networkId: String,
                          @RequestBody form: GameForm): GameDto {
        return gameService.updateByNetworkId(networkId, form)
    }

    @PutMapping("/network/{network-id}/subnetwork/{subnetwork-id}/game/{game-id}")
    fun updateBySubnetworkId(@PathVariable("subnetwork-id") subnetworkId: String,
                             @RequestBody form: GameForm): GameDto {
        return gameService.updateBySubnetwork(subnetworkId, form)
    }

    @GetMapping("/network/{network-id}/game")
    fun findByNetworkId(@PathVariable("network-id") networkId: String): List<GameDto> {
        return gameService.findByNetworkId(networkId)
    }

    @GetMapping("/network/{network-id}/subnetwork/{subnetwork-id}/game")
    fun findBySubnetworkId(@PathVariable("subnetwork-id") subnetworkId: String): List<GameDto> {
        return gameService.findBySubnetworkId(subnetworkId)
    }

    @DeleteMapping("/game/{game-id}")
    fun delete(@PathVariable("game-id") gameId: String) {
        return gameService.delete(gameId)
    }


}