package com.game.cardgame.player.dto;

import com.game.cardgame.base.dto.Dto;

public class PlayerDto extends Dto {
 
    private String name;

    private Long gameId;
      
    public String getName() {
        return name;
    }
      
    public void setName(String name) {
        this.name = name;
    }

    public Long getGameId() {
        return gameId;
    }
      
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}