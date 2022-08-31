package ru.games.rps.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.games.rps.BaseTest;
import ru.games.rps.entity.PlayerStatistics;
import ru.games.rps.entity.Result;
import ru.games.rps.mapper.PlayerStatisticsMapper;
import ru.games.rps.repository.PlayerStatisticsRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerStatisticsServiceTest extends BaseTest {

    @Autowired
    private PlayerStatisticsRepository playerStatisticsRepository;
    @Autowired
    private PlayerStatisticsService playerStatisticsService;
    @Autowired
    private PlayerStatisticsMapper playerStatisticsMapper;
    private PlayerStatistics playerStatistics;

    @BeforeEach
    void setUp() {
        playerStatistics = playerStatisticsRepository.save(new PlayerStatistics());
    }

    @AfterEach
    void tearDown() {
        playerStatisticsRepository.deleteAll();
    }

    @Test
    void getById() {
        assertEquals(playerStatisticsMapper.fromEntity(playerStatistics),
                playerStatisticsService.getById(playerStatistics.getPlayerId()));
    }

    @Test
    void update() {
        Long wins = playerStatistics.getWins();
        playerStatisticsService.update(playerStatistics.getPlayerId(), Result.WIN);
        playerStatistics = playerStatisticsRepository.findById(playerStatistics.getPlayerId()).orElseThrow();
        assertEquals(wins + 1, playerStatistics.getWins());
    }
}