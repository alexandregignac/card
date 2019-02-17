package com.game.cardgame.gameplay.dto;

public class PlayerInGame{

    private Long id;

    private String name;

    private Integer handValue;

    public Long getId() {
        return id;
    }
      
    public void setId(Long id) {
        this.id = id;
    }
      
    public String getName() {
        return name;
    }
      
    public void setName(String name) {
        this.name = name;
    }

    public Integer getHandValue() {
        return handValue;
    }
      
    public void setHandValue(Integer handValue) {
        this.handValue = handValue;
    }
}