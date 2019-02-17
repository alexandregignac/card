package com.game.cardgame.player.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.game.cardgame.game.entity.Game;

@Entity
@Table(name="PLAYER", uniqueConstraints={@UniqueConstraint(columnNames={"gameId", "name"}, name="player_in_game")})
public class Player {
 
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="gameId")
    private Game game;
 
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
      
    public String getName() {
        return name;
    }
      
    public void setName(String name) {
        this.name = name;
    }
}