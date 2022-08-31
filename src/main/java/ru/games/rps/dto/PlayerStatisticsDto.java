package ru.games.rps.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerStatisticsDto {
    /**
     * id
     */
    private Long playerId;
    /**
     * rounds
     */
    private Long rounds;
    /**
     * number of wins
     */
    private Long wins;
    /**
     * number of loses
     */
    private Long loses;
    /**
     * number of draws
     */
    private Long draws;

}
