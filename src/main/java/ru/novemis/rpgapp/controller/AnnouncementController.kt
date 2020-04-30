package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.announcement.AnnouncementDto
import ru.novemis.rpgapp.dto.announcement.AnnouncementForm
import ru.novemis.rpgapp.service.AnnouncementService

@CrossOrigin
@RestController
@RequestMapping("/announcement")
class AnnouncementController(
        private val announcementService: AnnouncementService
) {

    @PostMapping(consumes = ["application/json"])
    fun save(@RequestBody form: AnnouncementForm): AnnouncementDto {
        return announcementService.saveAnnouncement(form)
    }

    @GetMapping
    fun getAll(): Iterable<AnnouncementDto> {
        return announcementService.findAll()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String): AnnouncementDto {
        return announcementService.delete(id)
    }

    @GetMapping("/{id}/restore")
    fun restore(@PathVariable("id") id: String): AnnouncementDto {
        return announcementService.restore(id)
    }

}