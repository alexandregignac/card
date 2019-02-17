package com.game.cardgame.deck.bll;

import com.game.cardgame.card.dto.CardDto;
import com.game.cardgame.card.enums.Suit;
import com.game.cardgame.card.interfaces.CardService;
import com.game.cardgame.deck.dto.DeckDto;
import com.game.cardgame.deck.interfaces.DeckService;
import com.game.cardgame.deck.interfaces.DeckGeneratorService;
import com.game.cardgame.tools.interfaces.RandomGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeckGeneratorServiceImpl implements DeckGeneratorService {

    @Autowired
    private DeckService DeckService;

    @Autowired
    private CardService CardService;

    @Autowired
    private RandomGenerator RandomGenerator;

    public DeckDto createNewDeck(Long gameId){
        DeckDto deck = new DeckDto();
        Integer decksInGame = DeckService.numbersOfDeckInShoe(gameId);
        deck.setGameId(gameId);
        deck.setShoeOrder(decksInGame);
        deck = DeckService.create(deck);
        fillDeck(deck.getId());
        return deck;
    }

    private void fillDeck(Long deckId){
        Integer[] random52 = RandomGenerator.getRandomNumbers(52);
        for (Integer i = 0; i < 13; i++) {
            CardService.create(new CardDto(deckId, Suit.CLUB, i+1, random52[i]));
            CardService.create(new CardDto(deckId, Suit.DIAMOND, i+1, random52[i+13]));
            CardService.create(new CardDto(deckId, Suit.HEART, i+1, random52[i+26]));
            CardService.create(new CardDto(deckId, Suit.SPADE, i+1, random52[i+39]));
        }
    }
}