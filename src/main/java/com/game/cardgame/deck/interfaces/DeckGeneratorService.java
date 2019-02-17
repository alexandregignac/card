package com.game.cardgame.deck.interfaces;

import com.game.cardgame.deck.dto.DeckDto;

public interface DeckGeneratorService {

    public DeckDto createNewDeck(Long gameId);
}