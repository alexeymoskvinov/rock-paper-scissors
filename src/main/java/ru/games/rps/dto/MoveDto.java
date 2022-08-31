package ru.games.rps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.games.rps.entity.Move;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MoveDto {
    private Long playerId;
    @NotNull
    private Move move;
}
