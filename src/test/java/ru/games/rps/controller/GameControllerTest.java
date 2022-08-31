package ru.games.rps.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.games.rps.BaseTest;
import ru.games.rps.dto.GameDto;
import ru.games.rps.dto.MoveDto;
import ru.games.rps.dto.RoundDto;
import ru.games.rps.entity.Game;
import ru.games.rps.entity.Move;
import ru.games.rps.entity.Status;
import ru.games.rps.mapper.GameMapper;
import ru.games.rps.repository.GameRepository;
import ru.games.rps.repository.RoundRepository;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GameControllerTest extends BaseTest {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private RoundRepository roundRepository;

    private Game game;

    @BeforeEach
    void setUp() {
        game = gameRepository.save(new Game());
    }

    @AfterEach
    void tearDown() {
        roundRepository.deleteAll();
        gameRepository.deleteAll();
    }

    @Test
    void create() throws Exception {
        String uri = "/game";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        GameDto gameDto = mapFromJson(content, GameDto.class);
        assertNotNull(gameDto.getId());
        assertEquals(Status.STARTED, gameDto.getStatus());
    }

    @Test
    void terminate() throws Exception {
        String uri = "/game/%s";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(String.format(uri, game.getId()))).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        GameDto gameDto = mapFromJson(content, GameDto.class);
        assertEquals(Status.TERMINATED, gameDto.getStatus());
    }

    @Test
    void playRound() throws Exception {
        String uri = "/game/%s";
        MoveDto moveDto = new MoveDto();
        moveDto.setPlayerId(1L);
        moveDto.setMove(Move.PAPER);
        String inputJson = mapToJson(moveDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(String.format(uri, game.getId()))
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        RoundDto roundDto = mapFromJson(content, RoundDto.class);
        assertNotNull(roundDto.getComputerMove());
        assertNotNull(roundDto.getResult());
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}