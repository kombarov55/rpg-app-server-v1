package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dao.announcement.AnnouncementRepository
import ru.novemis.rpgapp.dto.announcement.AnnouncementDto
import ru.novemis.rpgapp.dto.announcement.AnnouncementForm
import ru.novemis.rpgapp.service.AnnouncementService

@CrossOrigin
@RestController
@RequestMapping("/announcement")
class AnnouncementController(
        private val announcementService: AnnouncementService,
        private val announcementRepository: AnnouncementRepository
) {

    @PostMapping(consumes = ["application/json"])
    fun saveAnnouncement(@RequestBody form: AnnouncementForm): AnnouncementDto {
        return announcementService.saveAnnouncement(form)
    }

    @GetMapping
    fun getAll(): Iterable<AnnouncementDto> {
        return announcementService.findAll()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String) {
        announcementRepository.deleteById(id)
    }

}