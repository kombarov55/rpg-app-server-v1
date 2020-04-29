package ru.novemis.rpgapp.dao.announcement

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.announcement.comment.Comment

interface CommentRepository : CrudRepository<Comment, String> {

}