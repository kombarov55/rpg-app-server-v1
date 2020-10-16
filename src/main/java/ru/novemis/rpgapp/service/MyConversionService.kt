package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.ConversionConverter
import ru.novemis.rpgapp.dto.game.dto.ConversionDto
import ru.novemis.rpgapp.dto.game.form.ConversionForm
import ru.novemis.rpgapp.repository.game.ConversionRepository
import javax.transaction.Transactional

@Component
open class MyConversionService(
        private val conversionRepository: ConversionRepository,
        private val conversionConverter: ConversionConverter
) {

    @Transactional
    open fun save(gameId: String, conversionForm: ConversionForm): ConversionDto {
        return conversionConverter.toDomain(gameId, conversionForm)
                .let { conversionRepository.save(it) }
                .let { conversionConverter.toDto(it) }
    }

    open fun findByGameId(gameId: String): List<ConversionDto> {
        return conversionRepository.findByGameId(gameId)
                .map { conversionConverter.toDto(it) }
    }

}