package ru.novemis.rpgapp.dto.useraccount.dto

data class UserAccountDto(
        var id: String = "",
        var userId: Long = 1,
        var firstName: String = "",
        var lastName: String = "",
        var photo50Url: String = "",
        var userAccountPreferences: UserAccountPreferencesDto = UserAccountPreferencesDto()
)