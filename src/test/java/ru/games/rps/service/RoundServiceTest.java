package ru.games.rps.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.games.rps.BaseTest;
import ru.games.rps.dto.RoundDto;
import ru.games.rps.entity.Game;
import ru.games.rps.entity.Move;
import ru.games.rps.entity.Result;
import ru.games.rps.repository.GameRepository;
import ru.games.rps.repository.RoundRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RoundServiceTest extends BaseTest {

    @Autowired
    private RoundService roundService;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private GameRepository gameRepository;

    private Game game;

    @BeforeEach
    void setUp() {
        game = gameRepository.save(new Game());
    }

    @AfterEach
    void tearDown() {
        roundRepository.deleteAll();
        gameRepository.deleteAll();
    }

    @Test
    void add() {
        RoundDto roundDto = roundService.add(game, Move.PAPER, Move.ROCK, Result.WIN);
        assertTrue(roundRepository.existsById(roundDto.getId()));
    }
}