package com.game.cardgame.card.bll;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.game.cardgame.base.bll.ServiceImpl;
import com.game.cardgame.card.dal.CardRepository;
import com.game.cardgame.card.dto.CardDto;
import com.game.cardgame.card.entity.Card;
import com.game.cardgame.card.enums.Status;
import com.game.cardgame.card.interfaces.CardMapper;
import com.game.cardgame.card.interfaces.CardService;
import com.game.cardgame.deck.entity.Deck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl extends ServiceImpl<Card, CardDto> implements CardService {

    @PersistenceContext
	private EntityManager em;

    @Autowired
    public CardServiceImpl(CardMapper cardMapper, CardRepository cardRepository) {
        super(cardMapper, cardRepository);
    }

    @Override
    protected void updateEntityFromDto(Card entity, CardDto dto) {
        entity.setDeck(em.getReference(Deck.class, dto.getDeckId()));
        entity.setDeckOrder(dto.getDeckOrder());
        entity.setOwner(dto.getOwner());
        entity.setStatus(dto.getStatus());
        entity.setSuit(dto.getSuit());
        entity.setValue(dto.getValue());
    }

    public List<CardDto> getDecksCards(Long deckId) {
        return ((CardRepository) getDal()).findByDeckId(deckId).stream()
            .map(entity -> getMapper().mapToDto(entity))
            .collect(Collectors.toList());
    }
    
    @Override
    public List<CardDto> getPlayersCards(Long playerId) {
        return ((CardRepository) getDal()).findByOwner(playerId).stream()
            .map(entity -> getMapper().mapToDto(entity))
            .collect(Collectors.toList());
    }

    public List<CardDto> getCardsRemaining(Long deckId) {
        return ((CardRepository) getDal()).findByDeckIdAndStatus(deckId, Status.ToDraw).stream()
            .map(entity -> getMapper().mapToDto(entity))
            .collect(Collectors.toList());
    }

    public List<CardDto> getCardsRemainingBySuit(Long deckId) {
        return ((CardRepository) getDal()).findByDeckIdAndStatusOrderBySuitAscValueDesc(deckId, Status.ToDraw).stream()
            .map(entity -> getMapper().mapToDto(entity))
            .collect(Collectors.toList());
    }

    public CardDto getNextCard(Long deckId) {
        Card card = ((CardRepository) getDal()).findTopByDeckIdAndStatusOrderByDeckOrder(deckId, Status.ToDraw);
        if (card == null) return null;
        return getMapper().mapToDto(card); 
    }
}