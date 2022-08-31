package ru.games.rps.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.games.rps.dto.PlayerStatisticsDto;
import ru.games.rps.service.PlayerStatisticsService;

/**
 * player statistic controller
 */
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class PlayerStatisticsController {

    private final PlayerStatisticsService playerStatisticsService;

    /**
     * get player statistic
     *
     * @param id player id
     * @return player statistic
     */
    @GetMapping("/{id}")
    public PlayerStatisticsDto getById(@PathVariable("id") Long id) {
        return playerStatisticsService.getById(id);
    }

}
