package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dao.AnnouncementRepository
import ru.novemis.rpgapp.dao.ImageLinkRepository
import ru.novemis.rpgapp.dao.UploadCacheRepository
import ru.novemis.rpgapp.model.announcement.Announcement
import ru.novemis.rpgapp.model.announcement.ImageLink

@CrossOrigin
@RestController
@RequestMapping("/announcement")
class AnnouncementController(
        private val announcementRepository: AnnouncementRepository,
        private val uploadCacheRepository: UploadCacheRepository,
        private val imageLinkRepository: ImageLinkRepository
) {

    @PostMapping(consumes = ["application/json"])
    fun saveAnnouncement(@RequestBody announcement: Announcement): Announcement {
        val links = uploadCacheRepository.findById(announcement.uploadUid!!)
        announcement.images = links.map { ImageLink("", announcement.id, it) }

        uploadCacheRepository.deleteById(announcement.uploadUid)

        return announcementRepository.save(announcement)
    }

    @GetMapping
    fun getAll(): Iterable<Announcement> {
        return announcementRepository.findAll()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String) {
        announcementRepository.deleteById(id)
    }

}