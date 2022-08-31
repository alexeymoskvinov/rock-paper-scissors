package ru.games.rps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.games.rps.entity.Game;
import ru.games.rps.entity.Move;
import ru.games.rps.entity.Round;
import ru.games.rps.entity.Result;
import ru.games.rps.repository.RoundRepository;

@Service
@RequiredArgsConstructor
public class RoundService {
    private final RoundRepository roundRepository;

    public Round add(Game game, Move playerMove, Move computerMove, Result result){
        Round newRound = new Round();
        newRound.setGame(game);
        newRound.setPlayerMove(playerMove);
        newRound.setComputerMove(computerMove);
        newRound.setResult(result);
        return roundRepository.save(newRound);
    }

}
