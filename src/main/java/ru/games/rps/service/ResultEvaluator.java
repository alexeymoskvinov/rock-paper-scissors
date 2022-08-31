package ru.games.rps.service;

import org.springframework.stereotype.Component;
import ru.games.rps.entity.Move;
import ru.games.rps.entity.Result;

import java.util.Optional;

@Component
public class ResultEvaluator {
    Result evaluateMoves(Move playerOneMove, Move playerTwoMove) {
        if (!Optional.ofNullable(playerOneMove).isPresent()) {
            return Result.LOSE;
        } else if (playerOneMove.equals(playerTwoMove)) {
            return Result.DRAW;
        } else if (playerTwoMove.losesTo().equals(playerOneMove)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }
}
