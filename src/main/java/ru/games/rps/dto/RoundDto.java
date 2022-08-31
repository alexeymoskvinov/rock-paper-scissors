package ru.games.rps.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.games.rps.entity.Move;
import ru.games.rps.entity.Result;

@Data
@NoArgsConstructor
public class RoundDto {
    /**
     * id
     */
    private Long id;

    /**
     * Move of the player
     */
    private Move playerMove;

    /**
     * Move of the computer
     */
    private Move computerMove;

    /**
     * round result
     */
    private Result result;

    /**
     * game dto
     */
    private GameDto gameDto;
}
