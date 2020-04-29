package ru.novemis.rpgapp.dao.announcement

import org.springframework.data.domain.Sort
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.announcement.comment.Comment

interface CommentRepository : CrudRepository<Comment, String> {

    fun findByAnnouncementId(announcementId: String, sort: Sort): List<Comment>

    fun countByAnnouncementId(announcementId: String): Long

}