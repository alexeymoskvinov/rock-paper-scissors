package ru.games.rps.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.games.rps.dto.PlayerStatisticsDto;
import ru.games.rps.entity.PlayerStatistics;
import ru.games.rps.entity.Result;
import ru.games.rps.mapper.PlayerStatisticsMapper;
import ru.games.rps.repository.PlayerStatisticsRepository;

import javax.persistence.EntityNotFoundException;

/**
 * Player statics service
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerStatisticsService {
    private final PlayerStatisticsRepository playerStatisticsRepository;
    private final PlayerStatisticsMapper playerStatisticsMapper;

    /**
     * get player statistic by player id
     * @param id player id
     * @return player statistic
     */
    @Transactional(readOnly = true)
    public PlayerStatisticsDto getById(Long id) {
        return playerStatisticsMapper.fromEntity(findById(id));
    }

    /**
     * update player statistics
     * @param playerId player id
     * @param result   result of round
     */
    @Transactional
    public void update(Long playerId, Result result) {
        PlayerStatistics playerStatistics;
        try {
            playerStatistics = findById(playerId);
        } catch (EntityNotFoundException ignored) {
            playerStatistics = new PlayerStatistics();
        }

        playerStatistics.setRounds(playerStatistics.getRounds() + 1);
        if (result.equals(Result.WIN)) {
            playerStatistics.setWins(playerStatistics.getWins() + 1);
        } else if (result.equals(Result.LOSE)) {
            playerStatistics.setLoses(playerStatistics.getLoses() + 1);
        } else {
            playerStatistics.setDraws(playerStatistics.getDraws() + 1);
        }

        playerStatisticsRepository.save(playerStatistics);
        log.info("A player statistics with id = {} has been updated", playerId);
    }

    /**
     * find player statistics by id
     * @param id player id
     * @return player statistics
     */
    private PlayerStatistics findById(Long id) {
        return playerStatisticsRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Player statistics not found by player id = {}", id);
                    throw new EntityNotFoundException("Player statistics not found by id = " + id);
                });
    }
}
