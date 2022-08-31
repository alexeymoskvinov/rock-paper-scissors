package ru.games.rps.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlayerStatistics {
    @Id
    private Long playerId;
    private Long rounds;
    private Long wins;
    private Long loses;
    private Long draws;

    public PlayerStatistics(Long playerId) {
        this.playerId = playerId;
        this.rounds = 0L;
        this.wins = 0L;
        this.loses = 0L;
        this.draws = 0L;
    }
}
