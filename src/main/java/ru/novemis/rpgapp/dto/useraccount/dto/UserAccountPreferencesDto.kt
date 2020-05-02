package ru.novemis.rpgapp.dto.useraccount.dto

data class UserAccountPreferencesDto(
        var favAnnouncementIds: List<String> = emptyList(),
        var respondedAnnouncementIds: List<String> = emptyList()
)