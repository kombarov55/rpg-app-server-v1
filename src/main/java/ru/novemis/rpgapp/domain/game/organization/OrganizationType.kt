package ru.novemis.rpgapp.domain.game.organization

enum class OrganizationType(val value: String) {
    COUNTRY("Страна"),
    INSTITUTION("Заведение"),
    HOUSE("Жилой дом"),
    SHIP("Корабль"),
    MARKETPLACE("Рынок");

    fun toDto(): OrganizationTypeDto {
        return OrganizationTypeDto(
                name = this.name,
                value = this.value
        )    }
}

data class OrganizationTypeForm(
        val name: String = "",
        val value: String = ""
) {
    fun toDomain(): OrganizationType {
        return OrganizationType.values()
                .find { it.name == this.name } ?: throw IllegalArgumentException()
    }
}

data class OrganizationTypeDto(
        val name: String,
        val value: String
)