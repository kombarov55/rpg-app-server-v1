package ru.novemis.rpgapp.service

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.CommentConverter
import ru.novemis.rpgapp.dao.announcement.CommentRepository
import ru.novemis.rpgapp.dto.announcement.CommentRqDto
import ru.novemis.rpgapp.dto.announcement.CommentRsDto

@Component
class CommentService(
        private val commentRepository: CommentRepository,
        private val commentConverter: CommentConverter
) {

    fun saveComment(rq: CommentRqDto): CommentRsDto {
        return commentConverter.toDto(
                commentRepository.save(
                        commentConverter.toDomain(rq))
        )
    }

    fun findByAnnouncementId(announcementId: String): List<CommentRsDto> {
        return commentRepository.findByAnnouncementId(announcementId, Sort.by(Sort.Direction.ASC, "creationDate"))
                .map { commentConverter.toDto(it) }
    }

}