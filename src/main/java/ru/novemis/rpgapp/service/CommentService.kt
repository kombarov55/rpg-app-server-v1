package ru.novemis.rpgapp.service

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.CommentConverter
import ru.novemis.rpgapp.dao.announcement.CommentRepository
import ru.novemis.rpgapp.dto.announcement.CommentDto
import ru.novemis.rpgapp.dto.announcement.CommentForm

@Component
class CommentService(
        private val commentRepository: CommentRepository,
        private val commentConverter: CommentConverter
) {

    fun saveComment(form: CommentForm): CommentDto {
        return commentConverter.toDto(
                commentRepository.save(
                        commentConverter.toDomain(form))
        )
    }

    fun findByAnnouncementId(announcementId: String): List<CommentDto> {
        return commentRepository.findByAnnouncementId(announcementId, Sort.by(Sort.Direction.ASC, "creationDate"))
                .map { commentConverter.toDto(it) }
    }


    fun deleteComment(commentId: String) {
        commentRepository.findById(commentId)
                .map { it.apply { deleted = true } }
                .map { commentRepository.save(it) }
    }

    fun restoreComponent(commentId: String): CommentDto {
        return commentRepository.findById(commentId)
                .map { it.apply { deleted = false } }
                .map { commentRepository.save(it) }
                .map { commentConverter.toDto(it) }
                .orElseThrow { IllegalArgumentException() }
    }

}