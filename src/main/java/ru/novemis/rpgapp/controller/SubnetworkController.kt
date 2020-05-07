package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.network.SubnetworkDto
import ru.novemis.rpgapp.dto.network.SubnetworkForm
import ru.novemis.rpgapp.service.SubnetworkService

@RestController
@RequestMapping("/network")
class SubnetworkController(
        private val subnetworkService: SubnetworkService
) {

    @PostMapping("/{network-id}/subnetwork")
    fun save(@PathVariable("network-id") networkId: String,
             @RequestBody subnetworkForm: SubnetworkForm): SubnetworkDto {
        subnetworkForm.networkId = networkId

        return subnetworkService.save(subnetworkForm)
    }

    @PutMapping("/{network-id}subnetwork/{subnetwork-id}")
    fun update(@PathVariable("subnetwork-id") subnetworkId: String,
               @RequestBody form: SubnetworkForm): SubnetworkDto {
        return subnetworkService.update(subnetworkId, form)
    }

    @GetMapping("/{network-id}/subnetwork")
    fun findByNetworkId(@PathVariable("network-id") networkId: String): List<SubnetworkDto> {
        return subnetworkService.findByNetworkId(networkId)
    }

    @DeleteMapping("/{network-id}/subnetwork/{subnetwork-id}")
    fun delete(@PathVariable("subnetwork-id") subnetworkId: String) {
        subnetworkService.delete(subnetworkId)
    }

}