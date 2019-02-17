package com.game.cardgame.deck.controller;

import com.game.cardgame.deck.dto.DeckDto;
import com.game.cardgame.deck.interfaces.DeckService;
import com.game.cardgame.deck.interfaces.DeckGeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeckController {
 
    @Autowired
    private DeckService deckService;

    @Autowired
    private DeckGeneratorService deckGeneratorService;
 
    @GetMapping("/api/Decks/{deckId}")
    public DeckDto getDeck(@PathVariable(name="deckId")Long deckId) {
        return deckService.get(deckId);
    }

    @PostMapping("/api/Decks/{gameId}")
    public DeckDto addDeck(@PathVariable(name="gameId")Long gameId){
        return deckGeneratorService.createNewDeck(gameId);
    }
}