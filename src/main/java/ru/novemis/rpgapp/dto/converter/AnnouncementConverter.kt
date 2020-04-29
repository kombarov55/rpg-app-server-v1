package ru.novemis.rpgapp.dto.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.dao.useraccount.UserAccountRepository
import ru.novemis.rpgapp.domain.announcement.Announcement
import ru.novemis.rpgapp.domain.announcement.GameType
import ru.novemis.rpgapp.domain.announcement.Sex
import ru.novemis.rpgapp.dto.announcement.AnnouncementDto
import java.util.*

@Component
class AnnouncementConverter(
        private val userAccountRepository: UserAccountRepository
) {
    fun toDomain(announcementDto: AnnouncementDto): Announcement {
        return Announcement(
                author = userAccountRepository.findByUserId(announcementDto.authorId),
                creationDate = Date(announcementDto.creationDate),
                title = announcementDto.title,
                gameType = GameType.valueOf(announcementDto.gameType),
                sex = announcementDto.sex?.let { Sex.valueOf(it) },
                minAge = announcementDto.minAge,
                maxAge = announcementDto.maxAge,
                description = announcementDto.description,
                anonymous = announcementDto.anonymous,
                commentsEnabled = announcementDto.commentsEnabled
        )
    }

    fun toDto(announcement: Announcement): AnnouncementDto {
        return AnnouncementDto(
                id = announcement.id,
                authorId = announcement.author!!.userId,
                imgSrc = announcement.author!!.photo50Url,
                authorFullName = announcement.author!!.firstName + " " + announcement.author!!.lastName,
                creationDate = announcement.creationDate!!.time,
                title = announcement.title!!,
                description = announcement.description!!,
                sex = announcement.sex?.name,
                gameType = announcement.gameType!!.name,
                minAge = announcement.minAge,
                maxAge = announcement.maxAge,
                anonymous = announcement.anonymous!!,
                commentsEnabled = announcement.commentsEnabled!!,
                //TODO: реализовать
                commentsCount = 1
        )
    }
}