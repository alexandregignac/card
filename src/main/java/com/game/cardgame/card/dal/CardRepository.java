package com.game.cardgame.card.dal;

import java.util.List;

import com.game.cardgame.card.entity.Card;
import com.game.cardgame.card.enums.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Long>{
    List<Card> findByDeckId(Long deckId);
    List<Card> findByOwner(Long owner);
    List<Card> findByDeckIdAndStatus(Long deckId, Status status);
    List<Card> findByDeckIdAndStatusOrderBySuitAscValueDesc(Long deckId, Status status);
    Card findTopByDeckIdAndStatusOrderByDeckOrder(Long deckId, Status status);
}