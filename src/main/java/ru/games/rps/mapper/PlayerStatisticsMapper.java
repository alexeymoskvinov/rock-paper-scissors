package ru.games.rps.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.games.rps.dto.PlayerStatisticsDto;
import ru.games.rps.entity.PlayerStatistics;

@Mapper(componentModel = "spring")
public interface PlayerStatisticsMapper {
    @Mapping(target = "playerId", source = "playerId")
    @Mapping(target = "rounds", source = "rounds")
    @Mapping(target = "wins", source = "wins")
    @Mapping(target = "loses", source = "loses")
    @Mapping(target = "draws", source = "draws")
    PlayerStatisticsDto fromEntity(PlayerStatistics playerStatistics);

    @Mapping(target = "playerId", source = "playerId")
    @Mapping(target = "rounds", source = "rounds")
    @Mapping(target = "wins", source = "wins")
    @Mapping(target = "loses", source = "loses")
    @Mapping(target = "draws", source = "draws")
    PlayerStatistics fromDto(PlayerStatisticsDto playerStatisticsDto);
}
