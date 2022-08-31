package ru.games.rps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.games.rps.dto.MoveDto;
import ru.games.rps.entity.*;
import ru.games.rps.exception.GameIsNotActiveException;
import ru.games.rps.repository.GameRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Random;

import static ru.games.rps.entity.Status.STARTED;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final ResultEvaluator resultEvaluator;
    private final RoundService roundService;
    private final PlayerStatisticsService playerStatisticsService;

    public Game startNew() {
        Game newGame = new Game();
        newGame.setStatus(STARTED);
        gameRepository.save(newGame);

        return newGame;
    }

    private Game findById(Long id) {
        return gameRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Game not found by id = " + id);
                });
    }

    public Game terminate(Long gameId) {
        Game game = findById(gameId);
        game.setStatus(Status.TERMINATED);
        gameRepository.save(game);
        return game;
    }


    public Round playNewRound(Game game, MoveDto moveDto) {

        if (!STARTED.equals(game.getStatus())) {
            throw new GameIsNotActiveException(game.getId());
        }

        Long playerId = moveDto.getPlayerId();
        Move playerMove = moveDto.getMove();
        Move computerMove = Move.values()[new Random().nextInt(Move.values().length)];
        Result result = resultEvaluator.evaluateMoves(playerMove, computerMove);

        playerStatisticsService.update(playerId, result);

        return roundService.add(game, playerMove, computerMove, result);
    }
}
