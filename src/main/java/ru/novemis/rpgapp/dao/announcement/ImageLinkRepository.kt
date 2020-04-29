package ru.novemis.rpgapp.dao.announcement

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.announcement.ImageLink

interface ImageLinkRepository : CrudRepository<ImageLink, String>