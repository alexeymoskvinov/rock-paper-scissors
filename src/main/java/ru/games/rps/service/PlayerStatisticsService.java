package ru.games.rps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.games.rps.entity.PlayerStatistics;
import ru.games.rps.entity.Result;
import ru.games.rps.repository.PlayerStatisticsRepository;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PlayerStatisticsService {
    private final PlayerStatisticsRepository playerStatisticsRepository;

    private PlayerStatistics findById(Long id) {
        return playerStatisticsRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Player statistics not found by id = " + id);
                });
    }


    public PlayerStatistics update(Long playerId, Result result){
        PlayerStatistics playerStatistics;
        if(!playerStatisticsRepository.existsById(playerId)){
            playerStatistics = new PlayerStatistics();
            playerStatistics.setPlayerId(playerId);
        }
        else {
            playerStatistics = findById(playerId);
        }

        playerStatistics.setRounds(playerStatistics.getRounds() + 1);
        if(result.equals(Result.WIN)){
            playerStatistics.setWins(playerStatistics.getWins() + 1);
        } else if (result.equals(Result.LOSE)) {
            playerStatistics.setLoses(playerStatistics.getLoses() + 1);
        }
        else {
            playerStatistics.setDraws(playerStatistics.getDraws() + 1);
        }

        return playerStatisticsRepository.save(playerStatistics);
    }
}
