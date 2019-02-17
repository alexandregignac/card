package com.game.cardgame.deck.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.game.cardgame.game.entity.Game;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="DECK")
public class Deck {
 
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="gameId")
    private Game game;
      
    @Column(name="shoeOrder")
  	private Integer shoeOrder;
 
    public Long getId() {
        return id;
    }
      
    public void setId(Long id) {
        this.id = id;
    }
      
    public Game getGame() {
        return game;
    }
      
    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getShoeOrder() {
        return shoeOrder;
    }
      
    public void setShoeOrder(Integer shoeOrder) {
        this.shoeOrder = shoeOrder;
    }
}