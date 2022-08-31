package ru.games.rps.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.games.rps.entity.Game;
import ru.games.rps.entity.Move;
import ru.games.rps.entity.Result;

import javax.persistence.*;

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
