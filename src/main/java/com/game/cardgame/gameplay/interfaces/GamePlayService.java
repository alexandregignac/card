package com.game.cardgame.gameplay.interfaces;

import java.util.List;

import com.game.cardgame.card.dto.CardDto;
import com.game.cardgame.gameplay.dto.PlayerInGame;
import com.game.cardgame.gameplay.dto.RemainingCardsSummary;

import org.springframework.stereotype.Service;

@Service
public interface GamePlayService  {

    public void dealCard(Long gameId, Long playerId);

    public void shuffleDeck(Long deckId);

    public List<PlayerInGame> getPlayers(Long gameId);

    public RemainingCardsSummary remainingCardsSummary(Long gameId);

    public List<CardDto> remainingCardsDetail(Long gameId);
}