package ru.novemis.rpgapp.dao

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.model.announcement.ImageLink

interface ImageLinkRepository : CrudRepository<ImageLink, String>