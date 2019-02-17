package com.game.cardgame.gameplay.controller;

import java.util.List;

import com.game.cardgame.card.dto.CardDto;
import com.game.cardgame.gameplay.dto.PlayerInGame;
import com.game.cardgame.gameplay.dto.RemainingCardsSummary;
import com.game.cardgame.gameplay.interfaces.GamePlayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GamePlayController {

    @Autowired
    private GamePlayService gamePlayService;
 
    @PostMapping("/api/GamePlay/Shuffle/{deckId}")
    public void shuffleDeck(@PathVariable(name="deckId")Long deckId) {
        gamePlayService.shuffleDeck(deckId);
    }
 
    @PostMapping("/api/GamePlay/{gameId}/Deal/{playerId}")
    public void dealCard(@PathVariable(name="gameId") Long gameId, @PathVariable(name="playerId") Long playerId) {
        gamePlayService.dealCard(gameId, playerId);
    }

    @GetMapping("/api/GamePlay/{gameId}/Players")
    public List<PlayerInGame> getPlayers(@PathVariable(name="gameId") Long gameId) {
        return gamePlayService.getPlayers(gameId);
    }

    @GetMapping("/api/GamePlay/{gameId}/CardsSummary")
    public RemainingCardsSummary getRemainingCardsSummary(@PathVariable(name="gameId") Long gameId) {
        return gamePlayService.remainingCardsSummary(gameId);
    }

    @GetMapping("/api/GamePlay/{gameId}/CardsDetail")
    public List<CardDto> getRemainingCardsDetail(@PathVariable(name="gameId") Long gameId) {
        return gamePlayService.remainingCardsDetail(gameId);
    }
}
