package ru.games.rps.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.games.rps.dto.GameDto;
import ru.games.rps.dto.MoveDto;
import ru.games.rps.dto.RoundDto;
import ru.games.rps.entity.Game;
import ru.games.rps.entity.Move;
import ru.games.rps.entity.Result;
import ru.games.rps.entity.Status;
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
@Slf4j
public class GameService {
    private static final Random RANDOM = new Random();
    private final GameRepository gameRepository;
    private final ResultEvaluator resultEvaluator;
    private final RoundService roundService;
    private final PlayerStatisticsService playerStatisticsService;
    private final GameMapper gameMapper;

    /**
     * start new game
     *
     * @return new game dto
     */
    @Transactional
    public GameDto startNew() {
        Game newGame = new Game();
        newGame = gameRepository.save(newGame);
        log.info("A Course with id = {} started ", newGame.getId());
        return gameMapper.fromEntity(newGame);
    }

    /**
     * terminate
     *
     * @param gameId game id
     * @return terminated game
     */
    @Transactional
    public GameDto terminate(Long gameId) {
        Game game = findById(gameId);
        if (game.getStatus() != STARTED) {
            throw new GameIsNotActiveException(gameId);
        }
        game.setStatus(Status.TERMINATED);
        game = gameRepository.save(game);
        log.info("A Course with id = {} terminated ", game.getId());
        return gameMapper.fromEntity(game);
    }

    /**
     * play new round
     *
     * @param gameId  game id
     * @param moveDto move dto with player move
     * @return new round
     */
    @Transactional
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

        RoundDto roundDto = roundService.add(game, playerMove, computerMove, result);

        log.info("Round was played by player with id = {}. With result - {}", playerId, result);

        return roundDto;
    }

    /**
     * find game by id
     *
     * @param id game id
     * @return game dto
     */

    private Game findById(Long id) {
        return gameRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Game not found by id = {}", id);
                    throw new EntityNotFoundException("Game not found by id = " + id);
                });
    }
}
