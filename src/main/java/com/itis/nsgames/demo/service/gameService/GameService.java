package com.itis.nsgames.demo.service.gameService;

import com.itis.nsgames.demo.dto.game.GameDto;
import com.itis.nsgames.demo.model.Game;

import java.util.List;
import java.util.Set;

public interface GameService {
    void putAll();
    List<GameDto> getAll();
    List<Game> getGamesByIds(List<Integer> gamesID);
}
