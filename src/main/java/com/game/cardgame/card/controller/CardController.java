package com.game.cardgame.card.controller;

import java.util.List;

import com.game.cardgame.card.dto.CardDto;
import com.game.cardgame.card.interfaces.CardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {
 
    @Autowired
    private CardService cardService;

    @GetMapping("/api/Deck/{deckId}/Cards")
    public List<CardDto> getCards(@PathVariable(name="deckId")Long deckId) {
        return cardService.getDecksCards(deckId);
    }

    @GetMapping("/api/Player/{playerId}/Cards")
    public List<CardDto> getPlayersCards(@PathVariable(name="playerId")Long playerId) {
        return cardService.getPlayersCards(playerId);
    }
}