package ru.games.rps.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "round", schema = "rps_schema")
public class Round {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Move of the player
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "player_move")
    private Move playerMove;

    /**
     * Move of the computer
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "computer_move")
    private Move computerMove;

    /**
     * round result
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "result")
    private Result result;

    /**
     * game
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    @JsonIgnore
    private Game game;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return id.equals(round.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
