package com.itis.nsgames.demo.controller.game;


import com.itis.nsgames.demo.dto.game.GameDto;
import com.itis.nsgames.demo.model.Game;
import com.itis.nsgames.demo.service.gameService.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/game/getAll")
    public ResponseEntity<List<GameDto>> getGame() {
        return ResponseEntity.ok(gameService.getAll());
    }

    @PostMapping("/game/putAll")
    public ResponseEntity<?> putAll() {
        gameService.putAll();
        return ResponseEntity.ok("Done");
    }
}
