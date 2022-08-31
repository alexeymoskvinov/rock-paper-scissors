package ru.games.rps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.games.rps.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
