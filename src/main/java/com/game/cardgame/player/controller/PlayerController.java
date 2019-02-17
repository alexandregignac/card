package com.game.cardgame.player.controller;

import com.game.cardgame.player.dto.PlayerDto;
import com.game.cardgame.player.interfaces.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
 
    @Autowired
    private PlayerService playerService;

 
    @GetMapping("/api/Players/{playerId}")
    public PlayerDto getPlayer(@PathVariable(name="playerId")Long playerId) {
        return playerService.get(playerId);
    }
 
    @PostMapping("/api/Game/{gameId}/AddPlayer/{name}")
    public PlayerDto addPlayer(@PathVariable(name="gameId")Long gameId, @PathVariable(name="name")String name){
        PlayerDto player = new PlayerDto();
        player.setGameId(gameId);
        player.setName(name);
        return playerService.create(player);
    }
 
    @DeleteMapping("/api/Players/{playerId}")
    public void deletePlayer(@PathVariable(name="playerId")Long playerId){
        playerService.delete(playerId);
    }
 
}