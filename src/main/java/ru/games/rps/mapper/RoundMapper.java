package ru.games.rps.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.games.rps.dto.RoundDto;
import ru.games.rps.entity.Round;

@Mapper(componentModel = "spring", uses = {GameMapper.class})
public interface RoundMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "playerMove", source = "playerMove")
    @Mapping(target = "computerMove", source = "computerMove")
    @Mapping(target = "result", source = "result")
    @Mapping(target = "gameDto", source = "game")
    RoundDto fromEntity(Round round);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "playerMove", source = "playerMove")
    @Mapping(target = "computerMove", source = "computerMove")
    @Mapping(target = "result", source = "result")
    @Mapping(target = "game", source = "gameDto")
    Round fromDto(RoundDto roundDto);
}
