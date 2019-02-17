package com.game.cardgame.card.interfaces;

import java.util.List;

import com.game.cardgame.card.dto.CardDto;

public interface CardService {

    public List<CardDto> getDecksCards(Long deckId);

    public List<CardDto> getPlayersCards(Long playerId);

    public List<CardDto> getCardsRemaining(Long deckId);

    public List<CardDto> getCardsRemainingBySuit(Long deckId);

    public CardDto getNextCard(Long deckId);

    public CardDto create(CardDto card);

    public void update(CardDto card);
}