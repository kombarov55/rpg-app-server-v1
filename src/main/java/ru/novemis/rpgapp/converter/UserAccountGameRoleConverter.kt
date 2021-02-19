package ru.novemis.rpgapp.converter

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import ru.novemis.rpgapp.domain.useraccount.UserAccountGameRole
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountGameRoleDto

@Mapper
interface UserAccountGameRoleConverter {
    @Mappings(
        Mapping(target = "title", source = "game.title"),
        Mapping(target = "role", source = "role.label"),
        Mapping(target = "gameId", source = "game.id")
    )
    fun toDto(domain: UserAccountGameRole): UserAccountGameRoleDto
}