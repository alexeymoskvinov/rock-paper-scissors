package ru.games.rps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.games.rps.entity.Round;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
}
