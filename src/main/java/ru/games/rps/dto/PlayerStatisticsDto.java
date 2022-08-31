package ru.games.rps.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.games.rps.entity.PlayerStatistics;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

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
