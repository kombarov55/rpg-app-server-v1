package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.dao.announcement.CommentRepository
import ru.novemis.rpgapp.dao.useraccount.UserAccountRepository
import ru.novemis.rpgapp.domain.announcement.Announcement
import ru.novemis.rpgapp.domain.announcement.GameType
import ru.novemis.rpgapp.domain.announcement.Sex
import ru.novemis.rpgapp.dto.announcement.AnnouncementRqDto
import ru.novemis.rpgapp.dto.announcement.AnnouncementRsDto
import java.util.*

@Component
class AnnouncementConverter(
        private val userAccountRepository: UserAccountRepository,
        private val commentRepository: CommentRepository
) {
    fun toDomain(rq: AnnouncementRqDto): Announcement {
        return Announcement(
                author = userAccountRepository.findByUserId(rq.authorId),
                creationDate = Date(),
                title = rq.title,
                gameType = GameType.valueOf(rq.gameType),
                sex = rq.sex?.let { Sex.valueOf(it) },
                minAge = rq.minAge,
                maxAge = rq.maxAge,
                description = rq.description,
                anonymous = rq.anonymous,
                commentsEnabled = rq.commentsEnabled
        )
    }

    fun toDto(announcement: Announcement): AnnouncementRsDto {
        return AnnouncementRsDto(
                id = announcement.id,
                authorId = announcement.author?.userId!!,
                imgSrc = announcement.author?.photo50Url!!,
                authorFullName = announcement.author?.let { it.firstName + " " + it.lastName }!!,
                creationDate = announcement.creationDate.time,
                title = announcement.title,
                description = announcement.description,
                sex = announcement.sex?.description,
                gameType = announcement.gameType?.description!!,
                minAge = announcement.minAge,
                maxAge = announcement.maxAge,
                anonymous = announcement.anonymous,
                commentsEnabled = announcement.commentsEnabled,
                commentsCount = commentRepository.countByAnnouncementId(announcement.id)
        )
    }
}