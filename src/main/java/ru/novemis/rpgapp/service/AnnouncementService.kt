package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.AnnouncementConverter
import ru.novemis.rpgapp.dao.announcement.AnnouncementRepository
import ru.novemis.rpgapp.dto.announcement.AnnouncementRqDto
import ru.novemis.rpgapp.dto.announcement.AnnouncementRsDto

@Component
class AnnouncementService(
        private val announcementRepository: AnnouncementRepository,
        private val announcementConverter: AnnouncementConverter
) {

    fun saveAnnouncement(announcementDto: AnnouncementRqDto): AnnouncementRsDto {
        return announcementConverter.toDto(
                announcementRepository.save(
                        announcementConverter.toDomain(announcementDto)))

    }

    fun findAll(): List<AnnouncementRsDto> {
        return announcementRepository.findAll()
                .map { announcementConverter.toDto(it) }
    }

}