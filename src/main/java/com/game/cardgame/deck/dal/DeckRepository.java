package com.game.cardgame.deck.dal;

import com.game.cardgame.deck.entity.Deck;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckRepository extends JpaRepository<Deck,Long>{
    Integer countByGameId(Long gameId);
    Deck findTopByGameIdOrderByShoeOrder(Long gameId);
}