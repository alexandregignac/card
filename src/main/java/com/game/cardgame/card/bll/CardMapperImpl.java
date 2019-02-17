package com.game.cardgame.card.bll;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.game.cardgame.base.bll.MapperImpl;
import com.game.cardgame.card.dto.CardDto;
import com.game.cardgame.card.entity.Card;
import com.game.cardgame.card.interfaces.CardMapper;
import com.game.cardgame.deck.entity.Deck;

import org.springframework.stereotype.Component;

@Component
public class CardMapperImpl extends MapperImpl<Card, CardDto> implements CardMapper {
        
    @PersistenceContext
    private EntityManager em;
    
    public CardMapperImpl() {
        super(Card::new, CardDto::new);
    }
    
    @Override
    public CardDto mapToDto(Card entity) {
        CardDto dto = super.mapToDto(entity);
        dto.setDeckId(entity.getDeck().getId());
        return dto;
    }

    @Override
    public Card mapToEntity(CardDto dto) {
        Card entity = super.mapToEntity(dto);
        entity.setDeck(em.getReference(Deck.class, dto.getDeckId()));
        return entity;
    }
}