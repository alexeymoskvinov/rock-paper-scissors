package ru.games.rps.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Game {
    @Id
    @Setter(value = AccessLevel.PRIVATE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "game", cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SUBSELECT)
    private List<Round> rounds = new ArrayList<>();

    public Game() {
        this.status = Status.STARTED;
    }
}
