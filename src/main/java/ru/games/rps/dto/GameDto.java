package ru.games.rps.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.games.rps.entity.Status;

import java.util.Objects;

@Data
@NoArgsConstructor
public class GameDto {
    /**
     * id
     */
    private Long id;
    /**
     * game status
     */
    private Status status;
}
