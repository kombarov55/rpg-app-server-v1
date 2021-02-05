package ru.novemis.rpgapp.converter;

import org.mapstruct.Mapper;
import ru.novemis.rpgapp.domain.game.common.Price;
import ru.novemis.rpgapp.dto.game.common.dto.PriceDto;

@Mapper
public interface SampleMapper {

    PriceDto toDto(Price domain);
}
