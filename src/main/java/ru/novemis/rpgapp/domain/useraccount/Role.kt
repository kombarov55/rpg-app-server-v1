package ru.novemis.rpgapp.domain.useraccount

enum class Role(val label: String) {
    MAIN_ADMIN("Главный администратор"),
    NET_ADMIN("Администратор сети"),
    SUBNET_ADMIN("Администратор подсети"),
    GAME_ADMIN("Администратор игры"),
    GAME_MASTER("Мастер игры"),
    QUESTIONNAIRE_MANAGER("Анкетолог"),
    PLAYER("Игрок"),
    VISITOR("Посетитель")
}