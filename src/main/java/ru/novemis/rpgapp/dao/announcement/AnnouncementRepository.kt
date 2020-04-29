package ru.novemis.rpgapp.dao.announcement

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.announcement.Announcement

interface AnnouncementRepository : CrudRepository<Announcement, String> {

    @Query("select a from Announcement a where a.deleted = false")
    override fun findAll(): List<Announcement>
}