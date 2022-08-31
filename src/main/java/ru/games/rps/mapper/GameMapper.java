package ru.games.rps.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.games.rps.dto.GameDto;
import ru.games.rps.entity.Game;

@Mapper(componentModel = "spring")
public interface GameMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "status", source = "status")
    GameDto fromEntity(Game game);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "status", source = "status")
    Game fromDto(GameDto gameDto);
}
