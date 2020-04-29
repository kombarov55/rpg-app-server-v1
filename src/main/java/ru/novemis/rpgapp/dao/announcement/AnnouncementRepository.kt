package ru.novemis.rpgapp.dao.announcement

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.announcement.Announcement

interface AnnouncementRepository : CrudRepository<Announcement, String>