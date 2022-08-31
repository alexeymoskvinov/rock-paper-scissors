package ru.games.rps.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.games.rps.BaseTest;
import ru.games.rps.dto.GameDto;
import ru.games.rps.dto.PlayerStatisticsDto;
import ru.games.rps.entity.PlayerStatistics;
import ru.games.rps.entity.Status;
import ru.games.rps.mapper.PlayerStatisticsMapper;
import ru.games.rps.repository.PlayerStatisticsRepository;
import ru.games.rps.service.PlayerStatisticsService;

import static org.junit.jupiter.api.Assertions.*;

class PlayerStatisticsControllerTest extends BaseTest {
    @Autowired
    private PlayerStatisticsRepository playerStatisticsRepository;
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
    void getById() throws Exception {
        String uri = "/statistics/%s";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(String.format(uri, playerStatistics.getPlayerId()))).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        PlayerStatisticsDto playerStatisticsDto = mapFromJson(content, PlayerStatisticsDto.class);
        assertEquals(playerStatisticsMapper.fromEntity(playerStatisticsRepository.findById(playerStatisticsDto.getPlayerId()).orElseThrow()), playerStatisticsDto);
    }
}