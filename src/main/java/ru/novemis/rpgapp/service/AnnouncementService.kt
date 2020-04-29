package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.AnnouncementConverter
import ru.novemis.rpgapp.dao.announcement.AnnouncementRepository
import ru.novemis.rpgapp.dto.announcement.AnnouncementDto
import ru.novemis.rpgapp.dto.announcement.AnnouncementForm

@Component
class AnnouncementService(
        private val announcementRepository: AnnouncementRepository,
        private val announcementConverter: AnnouncementConverter
) {

    fun saveAnnouncement(form: AnnouncementForm): AnnouncementDto {
        return announcementConverter.toDto(
                announcementRepository.save(
                        announcementConverter.toDomain(form)))

    }

    fun findAll(): List<AnnouncementDto> {
        return announcementRepository.findAll()
                .map { announcementConverter.toDto(it) }
    }

    fun delete(announcementId: String) {

    }

}