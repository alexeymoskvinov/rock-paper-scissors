package ru.games.rps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.games.rps.dto.GameDto;
import ru.games.rps.dto.MoveDto;
import ru.games.rps.dto.RoundDto;
import ru.games.rps.entity.*;
import ru.games.rps.exception.GameIsNotActiveException;
import ru.games.rps.mapper.GameMapper;
import ru.games.rps.repository.GameRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Random;

import static ru.games.rps.entity.Status.STARTED;

/**
 * game service
 */
@Service
@RequiredArgsConstructor
public class GameService {
    private static final Random RANDOM = new Random();
    private final GameRepository gameRepository;
    private final ResultEvaluator resultEvaluator;
    private final RoundService roundService;
    private final PlayerStatisticsService playerStatisticsService;
    private final GameMapper gameMapper;

    /**
     * start new game
     * @return new game dto
     */
    public GameDto startNew() {
        Game newGame = new Game();
        return gameMapper.fromEntity( gameRepository.save(newGame));
    }

    /**
     * terminate
     * @param gameId game id
     * @return terminated game
     */
    public GameDto terminate(Long gameId) {
        Game game = findById(gameId);
        if (game.getStatus() != STARTED) {
            throw new GameIsNotActiveException(gameId);
        }
        game.setStatus(Status.TERMINATED);
        return gameMapper.fromEntity(gameRepository.save(game));
    }

    /**
     * play new round
     * @param gameId game id
     * @param moveDto move dto with player move
     * @return new round
     */
    public RoundDto playNewRound(Long gameId, MoveDto moveDto) {

        Game game = findById(gameId);

        if (!STARTED.equals(game.getStatus())) {
            throw new GameIsNotActiveException(game.getId());
        }

        Long playerId = moveDto.getPlayerId();
        Move playerMove = moveDto.getMove();
        Move computerMove = Move.values()[RANDOM.nextInt(Move.values().length)];
        Result result = resultEvaluator.evaluateMoves(playerMove, computerMove);

        playerStatisticsService.update(playerId, result);

        return roundService.add(game, playerMove, computerMove, result);
    }

    /**
     * find game by id
     * @param id game id
     * @return game dto
     */
    private Game findById(Long id) {
        return gameRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Game not found by id = " + id);
                });
    }
}
