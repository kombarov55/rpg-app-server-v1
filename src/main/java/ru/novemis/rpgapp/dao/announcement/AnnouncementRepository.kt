package ru.novemis.rpgapp.dao.announcement

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.model.announcement.Announcement

interface AnnouncementRepository : CrudRepository<Announcement, String>