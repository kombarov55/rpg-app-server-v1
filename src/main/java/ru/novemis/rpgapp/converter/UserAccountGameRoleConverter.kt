package ru.novemis.rpgapp.converter

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.novemis.rpgapp.domain.useraccount.UserAccountGameRole
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountGameRoleDto

@Mapper
interface UserAccountGameRoleConverter {
    @Mapping(target = "title", source = "game.title")
    fun toDto(domain: UserAccountGameRole): UserAccountGameRoleDto
}