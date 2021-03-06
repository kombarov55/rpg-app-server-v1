package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.announcement.comment.Comment
import ru.novemis.rpgapp.dto.announcement.dto.CommentDto
import ru.novemis.rpgapp.dto.announcement.form.CommentForm
import ru.novemis.rpgapp.repository.announcement.AnnouncementRepository
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import java.util.*

@Component
class CommentConverter(
        private val userAccountRepository: UserAccountRepository,
        private val announcementRepository: AnnouncementRepository
) {

    fun toDomain(form: CommentForm): Comment {
        return Comment(
                author = userAccountRepository.findByUserId(form.authorId),
                announcement = announcementRepository.findById(form.announcementId).orElseThrow {IllegalArgumentException()},
                creationDate = Date(),
                text = form.text

        )
    }

    fun toDto(comment: Comment): CommentDto {
        return CommentDto(
                id = comment.id!!,
                announcementId = comment.announcement?.id!!,
                authorFullName = comment.author?.let { it.firstName + " " + it.lastName }!!,
                authorImgSrc = comment.author?.photo50Url!!,
                creationDate = comment.creationDate.time,
                text = comment.text,
                deleted = comment.deleted
        )
    }

}