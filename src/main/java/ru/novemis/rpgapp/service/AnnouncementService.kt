package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.AnnouncementConverter
import ru.novemis.rpgapp.dao.announcement.AnnouncementRepository
import ru.novemis.rpgapp.dto.announcement.AnnouncementDto

@Component
class AnnouncementService(
        private val announcementRepository: AnnouncementRepository,
        private val announcementConverter: AnnouncementConverter
) {

    fun saveAnnouncement(announcementDto: AnnouncementDto): AnnouncementDto {
        val domain = announcementConverter.toDomain(announcementDto)
        val announcement = announcementRepository.save(domain)

        val rs = announcementConverter.toDto(announcement)

        return rs
    }

    fun findAll(): List<AnnouncementDto> {
        return announcementRepository.findAll()
                .map { announcementConverter.toDto(it) }
    }

}