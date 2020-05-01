package ru.novemis.rpgapp.repository.announcement

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.announcement.comment.Comment

interface CommentRepository : CrudRepository<Comment, String> {

    @Query("select c from Comment c where c.deleted = false")
    fun findByAnnouncementId(announcementId: String, sort: Sort): List<Comment>

    @Query("select count(c) from Comment c where c.deleted = false and c.announcement.id = :announcementId")
    fun countByAnnouncementId(announcementId: String): Long

}