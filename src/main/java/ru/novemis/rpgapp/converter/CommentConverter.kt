package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.dao.announcement.AnnouncementRepository
import ru.novemis.rpgapp.dao.useraccount.UserAccountRepository
import ru.novemis.rpgapp.domain.announcement.comment.Comment
import ru.novemis.rpgapp.dto.announcement.CommentRqDto
import ru.novemis.rpgapp.dto.announcement.CommentRsDto
import java.util.*

@Component
class CommentConverter(
        private val userAccountRepository: UserAccountRepository,
        private val announcementRepository: AnnouncementRepository
) {

    fun toDomain(rqDto: CommentRqDto): Comment {
        return Comment(
                author = userAccountRepository.findByUserId(rqDto.authorId),
                announcement = announcementRepository.findById(rqDto.announcementId).orElseThrow {IllegalArgumentException()},
                creationDate = Date(),
                text = rqDto.text

        )
    }

    fun toDto(comment: Comment): CommentRsDto {
        return CommentRsDto(
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