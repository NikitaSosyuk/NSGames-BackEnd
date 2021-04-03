package com.itis.nsgames.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @GetMapping("/game")
    public ResponseEntity<String> getGame() {
        return ResponseEntity.ok("Hello world!");
    }

}
