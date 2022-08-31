package ru.games.rps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.games.rps.dto.RoundDto;
import ru.games.rps.entity.Game;
import ru.games.rps.entity.Move;
import ru.games.rps.entity.Result;
import ru.games.rps.entity.Round;
import ru.games.rps.mapper.RoundMapper;
import ru.games.rps.repository.RoundRepository;

/**
 * round service
 */
@Service
@RequiredArgsConstructor
public class RoundService {
    private final RoundRepository roundRepository;
    private final RoundMapper roundMapper;

    /**
     * add new round
     *
     * @param game         game
     * @param playerMove   player move
     * @param computerMove computer move
     * @param result       round result
     * @return new round
     */
    @Transactional
    public RoundDto add(Game game, Move playerMove, Move computerMove, Result result) {
        Round newRound = new Round();
        newRound.setGame(game);
        newRound.setPlayerMove(playerMove);
        newRound.setComputerMove(computerMove);
        newRound.setResult(result);
        return roundMapper.fromEntity(roundRepository.save(newRound));
    }

}
