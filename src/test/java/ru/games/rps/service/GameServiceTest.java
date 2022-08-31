package ru.games.rps.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.games.rps.BaseTest;
import ru.games.rps.dto.GameDto;
import ru.games.rps.dto.MoveDto;
import ru.games.rps.dto.RoundDto;
import ru.games.rps.entity.Game;
import ru.games.rps.entity.Move;
import ru.games.rps.entity.Status;
import ru.games.rps.repository.GameRepository;
import ru.games.rps.repository.RoundRepository;

import static org.junit.jupiter.api.Assertions.*;


class GameServiceTest extends BaseTest {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private RoundRepository roundRepository;

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
    void startNew() {
        GameDto gameDto = gameService.startNew();
        assertTrue(gameRepository.existsById(gameDto.getId()));
        assertEquals(Status.STARTED, gameDto.getStatus());
    }

    @Test
    void terminate() {
        GameDto gameDto = assertDoesNotThrow(() -> gameService.terminate(game.getId()));
        assertEquals(Status.TERMINATED, gameDto.getStatus());
    }

    @Test
    void playNewRound() {
        MoveDto moveDto = new MoveDto();
        moveDto.setPlayerId(1L);
        moveDto.setMove(Move.PAPER);
        RoundDto roundDto = gameService.playNewRound(game.getId(), moveDto);
        assertNotNull(roundDto.getComputerMove());
        assertNotNull(roundDto.getResult());
    }
}