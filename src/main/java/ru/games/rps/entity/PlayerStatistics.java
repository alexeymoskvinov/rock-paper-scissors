package ru.games.rps.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "player_statistics", schema = "rps_schema")
public class PlayerStatistics {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public PlayerStatistics() {
        this.rounds = 0L;
        this.wins = 0L;
        this.loses = 0L;
        this.draws = 0L;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerStatistics that = (PlayerStatistics) o;
        return playerId.equals(that.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }
}
