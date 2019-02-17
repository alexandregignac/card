package com.game.cardgame.card.dto;

import com.game.cardgame.base.dto.Dto;
import com.game.cardgame.card.enums.Status;
import com.game.cardgame.card.enums.Suit;

public class CardDto extends Dto {

    private Long deckId;
  
    private Long owner;

    private Integer value;

    private Suit suit;
      
    private Integer deckOrder;
      
    private Status status;

    public CardDto(){
    }

    public CardDto(Long deckId, Suit suit, Integer value, Integer deckOrder){
        this.deckId = deckId;
        this.suit = suit;
        this.value = value;
        this.deckOrder = deckOrder;
        this.status = Status.ToDraw;
    }
      
    public Long getDeckId() {
        return deckId;
    }
      
    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }

    public Long getOwner() {
        return owner;
    }
      
    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public Integer getValue() {
        return value;
    }
      
    public void setValue(Integer value) {
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }
      
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Integer getDeckOrder() {
        return deckOrder;
    }
      
    public void setDeckOrder(Integer deckOrder) {
        this.deckOrder = deckOrder;
    }

    public Status getStatus() {
        return status;
    }
      
    public void setStatus(Status status) {
        this.status = status;
    }
}