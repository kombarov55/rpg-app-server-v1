package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dao.AnnouncementRepository
import ru.novemis.rpgapp.dao.UploadCacheRepository
import ru.novemis.rpgapp.model.announcement.Announcement

@RestController
@RequestMapping("/announcement")
class AnnouncementController(
        private val announcementRepository: AnnouncementRepository,
        private val uploadCacheRepository: UploadCacheRepository
) {

    @PostMapping(consumes = ["application/json"])
    fun saveAnnouncement(@RequestBody announcement: Announcement): Announcement {
        val links = uploadCacheRepository.findById(announcement.uploadUid!!)
        // взять все ссылки по id из dto. из них сделать лист и прихерачить к announcmeent.
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