package com.game.cardgame.gameplay.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.game.cardgame.card.dto.CardDto;
import com.game.cardgame.card.enums.Status;
import com.game.cardgame.card.enums.Suit;
import com.game.cardgame.card.interfaces.CardService;
import com.game.cardgame.deck.dto.DeckDto;
import com.game.cardgame.deck.interfaces.DeckService;
import com.game.cardgame.gameplay.dto.PlayerInGame;
import com.game.cardgame.gameplay.dto.RemainingCardsSummary;
import com.game.cardgame.gameplay.interfaces.GamePlayService;
import com.game.cardgame.player.dto.PlayerDto;
import com.game.cardgame.player.interfaces.PlayerService;
import com.game.cardgame.tools.interfaces.RandomGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamePlayServiceImpl implements GamePlayService  {

    @Autowired
    private DeckService deckService;

    @Autowired
    private CardService cardService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private RandomGenerator randomGenerator;

    public void dealCard(Long gameId, Long playerId){
        DeckDto deck = deckService.getDeckInPlay(gameId);
        CardDto card = cardService.getNextCard(deck.getId());
        if (card == null) return;

        card.setOwner(playerId);
        card.setStatus(Status.Dealt);
        card.setDeckOrder(0);
        cardService.update(card);
    }

    public void shuffleDeck(Long deckId){
        List<CardDto> cards = cardService.getDecksCards(deckId);
        Integer[] random = randomGenerator.getRandomNumbers(cards.size());
        for (Integer i = 0; i < cards.size(); i++) {
            CardDto card = cards.get(i);
            card.setDeckOrder(random[i]);
            cardService.update(card);
        }
    }

    public List<PlayerInGame> getPlayers(Long gameId){
        List<PlayerInGame> playersInGame = new ArrayList<PlayerInGame>();
        List<PlayerDto> players = playerService.getAllInGame(gameId);
        for (PlayerDto player : players) {
            PlayerInGame playerInGame = new PlayerInGame();
            playerInGame.setId(player.getId());
            playerInGame.setName(player.getName());
            List<CardDto> playerHand = cardService.getPlayersCards(player.getId());
            playerInGame.setHandValue(playerHand.stream().mapToInt(c -> c.getValue()).sum());
            playersInGame.add(playerInGame);
        }

        return playersInGame.stream().sorted((p1, p2)->p2.getHandValue()
                                     .compareTo(p1.getHandValue()))
                                     .collect(Collectors.toList());
    }

    public RemainingCardsSummary remainingCardsSummary(Long gameId){
        RemainingCardsSummary summary = new RemainingCardsSummary();
        DeckDto deck = deckService.getDeckInPlay(gameId);
        List<CardDto> cards = cardService.getCardsRemaining(deck.getId());

        summary.setClub(getRemaningInSuit(cards, Suit.CLUB));
        summary.setDiamond(getRemaningInSuit(cards, Suit.DIAMOND));
        summary.setHeart(getRemaningInSuit(cards, Suit.HEART));
        summary.setSpade(getRemaningInSuit(cards, Suit.SPADE));
        
        return summary;
    }

    public List<CardDto> remainingCardsDetail(Long gameId){
        DeckDto deck = deckService.getDeckInPlay(gameId);
        return cardService.getCardsRemainingBySuit(deck.getId());
    }

    private Integer getRemaningInSuit(List<CardDto> cards, Suit suit){
        return cards.stream().filter(c -> c.getSuit() == suit).mapToInt(e -> 1).sum();
    }
}