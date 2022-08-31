package ru.games.rps.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.games.rps.dto.GameDto;
import ru.games.rps.dto.MoveDto;
import ru.games.rps.dto.RoundDto;
import ru.games.rps.service.GameService;

import javax.validation.Valid;

/**
 * game controller
 */
@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    /**
     * create new game
     *
     * @return game dto
     */
    @PostMapping
    public GameDto create() {
        return gameService.startNew();
    }

    /**
     * terminate game
     *
     * @param id game id
     * @return game dto
     */
    @DeleteMapping("/{id}")
    public GameDto terminate(@PathVariable("id") Long id) {
        return gameService.terminate(id);
    }

    /**
     * play new round
     *
     * @param id            game id
     * @param playerMoveDto player move dto
     * @return new round dto
     */
    @PutMapping("/{id}")
    public RoundDto playRound(@PathVariable Long id, @RequestBody @Valid MoveDto playerMoveDto) {
        return gameService.playNewRound(id, playerMoveDto);
    }

}
