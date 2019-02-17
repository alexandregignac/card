package com.game.cardgame.deck.interfaces;

import com.game.cardgame.deck.dto.DeckDto;

public interface DeckService {

    public DeckDto get(Long deckId);
    
    public void update(DeckDto deck);

    public DeckDto create(DeckDto deck);

    public Integer numbersOfDeckInShoe(Long gameId);

    public DeckDto getDeckInPlay(Long gameId);
}