package com.game.cardgame.card.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.game.cardgame.card.enums.Status;
import com.game.cardgame.card.enums.Suit;
import com.game.cardgame.deck.entity.Deck;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="CARD", uniqueConstraints={@UniqueConstraint(columnNames={"deckId", "value", "suit"}, name="cards_in_deck")})
public class Card {
 
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="deckId")
    private Deck deck;
  
    @Column(name="ownerid")
    private Long owner;

    @Column(name="value")
    private Integer value;

    @Column(name="suit")
    private Suit suit;
      
    @Column(name="deckOrder")
    private Integer deckOrder;
      
    @Column(name="status")
    private Status status;

    public Card(){
    }

    public Card(Deck deck, Suit suit, Integer value, Integer deckOrder){
        this.deck = deck;
        this.suit = suit;
        this.value = value;
        this.deckOrder = deckOrder;
    }
 
    public Long getId() {
        return id;
    }
      
    public void setId(Long id) {
        this.id = id;
    }
      
    public Deck getDeck() {
        return deck;
    }
      
    public void setDeck(Deck deck) {
        this.deck = deck;
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