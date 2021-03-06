package ru.novemis.rpgapp.domain.game.shop

enum class ArithmeticModifier {
    PLUS, MINUS, MULTIPLY, DIVIDE;

    fun toDto() = ArithmeticModifierDto(
            name = when (this) {
                PLUS -> "+"
                MINUS -> "-"
                MULTIPLY -> "*"
                DIVIDE -> "/"
            },
            value = this.name
    )

    fun calculate(initialAmount: Int, amount: Int): Int = when (this) {
        PLUS -> initialAmount + amount
        MINUS -> initialAmount - amount
        MULTIPLY -> initialAmount * amount
        DIVIDE -> initialAmount / amount
    }
}

data class ArithmeticModifierDto(
        val name: String,
        val value: String
)

data class ArithmeticModifierForm(
        var name: String = "",
        var value: String = ""
) {
    fun toDomain() = ArithmeticModifier.values()
            .find { it.name == this.value }
            ?: throw IllegalArgumentException()
}
