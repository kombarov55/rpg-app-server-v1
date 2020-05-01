package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.announcement.Announcement
import ru.novemis.rpgapp.domain.announcement.GameType
import ru.novemis.rpgapp.domain.announcement.Sex
import ru.novemis.rpgapp.dto.announcement.AnnouncementDto
import ru.novemis.rpgapp.dto.announcement.AnnouncementForm
import ru.novemis.rpgapp.repository.announcement.CommentRepository
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import java.util.*

@Component
class AnnouncementConverter(
        private val userAccountRepository: UserAccountRepository,
        private val commentRepository: CommentRepository
) {
    fun toDomain(form: AnnouncementForm): Announcement {
        return Announcement(
                author = userAccountRepository.findByUserId(form.authorId),
                creationDate = Date(),
                title = form.title,
                gameType = GameType.valueOf(form.gameType),
                sex = form.sex?.let { Sex.valueOf(it) },
                minAge = form.minAge,
                maxAge = form.maxAge,
                description = form.description,
                anonymous = form.anonymous,
                commentsEnabled = form.commentsEnabled
        )
    }

    fun toDto(announcement: Announcement): AnnouncementDto {
        return AnnouncementDto(
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