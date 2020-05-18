package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.Conversion
import ru.novemis.rpgapp.dto.game.ConversionDto
import ru.novemis.rpgapp.dto.game.ConversionForm
import ru.novemis.rpgapp.repository.game.CurrencyRepository
import ru.novemis.rpgapp.repository.game.GameRepository

@Component
class ConversionConverter(
        private val currencyRepository: CurrencyRepository,
        private val gameRepository: GameRepository
) {

    fun toDomain(gameId: String, conversionForm: ConversionForm): Conversion {
        return Conversion(
                currency1 = currencyRepository.findById(conversionForm.currency1!!.id!!).orElseThrow { IllegalArgumentException() },
                currency2 = currencyRepository.findById(conversionForm.currency2!!.id!!).orElseThrow { IllegalArgumentException() },
                conversionPrice1to2 = conversionForm.conversionPrice1to2,
                conversionPrice2to1 = conversionForm.conversionPrice2to1,
                game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException() }
        )
    }

    fun toDto(conversion: Conversion): ConversionDto {
        return ConversionDto(
                id = conversion.id,
                currency1 = conversion.currency1!!.name,
                currency2 = conversion.currency2!!.name,
                conversionPrice1to2 = conversion.conversionPrice1to2,
                conversionPrice2to1 = conversion.conversionPrice2to1
        )
    }

}