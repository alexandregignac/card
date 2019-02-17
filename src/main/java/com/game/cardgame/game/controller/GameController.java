package com.game.cardgame.game.controller;

import com.game.cardgame.game.interfaces.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
 
    @Autowired
    private GameService GameService;
 
    public void setGameService(GameService GameService) {
        this.GameService = GameService;
    }

    @PostMapping("/api/Games/")
    public Long createNewGame() {
        return GameService.createNewGame();
    }
 
    @DeleteMapping("/api/Games/{gameId}")
    public void deleteGame(@PathVariable(name="gameId")Long gameId){
        GameService.deleteGame(gameId);
    }
}