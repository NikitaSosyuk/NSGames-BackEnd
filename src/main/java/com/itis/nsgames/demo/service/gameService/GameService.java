package com.itis.nsgames.demo.service.gameService;

import com.itis.nsgames.demo.dto.game.GameDto;
import com.itis.nsgames.demo.model.Game;

import java.util.List;
import java.util.Set;

public interface GameService {
    List<GameDto> getAll();
    Set<Game> getGamesByIds(Set<Integer> gamesID);
    void putAll();
}
