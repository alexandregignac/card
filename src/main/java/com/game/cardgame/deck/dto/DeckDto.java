package com.game.cardgame.deck.dto;

import com.game.cardgame.base.dto.Dto;

public class DeckDto extends Dto {
 
    private Long gameId;
      
  	private Integer shoeOrder;
      
    public Long getGameId() {
        return gameId;
    }
      
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Integer getShoeOrder() {
        return shoeOrder;
    }
      
    public void setShoeOrder(Integer shoeOrder) {
        this.shoeOrder = shoeOrder;
    }
}