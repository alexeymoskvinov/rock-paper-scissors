package ru.games.rps.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "game", schema = "rps_schema")
public class Game {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * game status
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;


    /**
     * rounds of the game
     */
    @OneToMany(mappedBy = "game", cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SUBSELECT)
    private List<Round> rounds = new ArrayList<>();

    public Game() {
        this.status = Status.STARTED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id.equals(game.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
