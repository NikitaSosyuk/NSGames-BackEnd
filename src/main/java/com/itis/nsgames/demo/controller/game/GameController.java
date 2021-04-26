package com.itis.nsgames.demo.controller.game;


import com.itis.nsgames.demo.dto.game.GameDto;
import com.itis.nsgames.demo.model.Game;
import com.itis.nsgames.demo.service.gameService.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/game/getAll")
    public ResponseEntity<List<GameDto>> getAllGames() {
        return ResponseEntity.ok(gameService.getAll());
    }

    @PostMapping("/game/putAll")
    public ResponseEntity<?> putAll() {
        gameService.putAll();
        return ResponseEntity.ok("Done");
    }
}
