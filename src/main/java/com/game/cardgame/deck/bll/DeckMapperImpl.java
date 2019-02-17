package com.game.cardgame.deck.bll;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.game.cardgame.base.bll.MapperImpl;
import com.game.cardgame.deck.dto.DeckDto;
import com.game.cardgame.deck.entity.Deck;
import com.game.cardgame.deck.interfaces.DeckMapper;
import com.game.cardgame.game.entity.Game;

import org.springframework.stereotype.Component;

@Component
public class DeckMapperImpl extends MapperImpl<Deck, DeckDto> implements DeckMapper {
        
    @PersistenceContext
    private EntityManager em;
    
    public DeckMapperImpl() {
        super(Deck::new, DeckDto::new);
    }
    
    @Override
    public DeckDto mapToDto(Deck entity) {
        DeckDto dto = super.mapToDto(entity);
        dto.setGameId(entity.getGame().getId());
        return dto;
    }

    @Override
    public Deck mapToEntity(DeckDto dto) {
        Deck entity = super.mapToEntity(dto);
        entity.setGame(em.getReference(Game.class, dto.getGameId()));
        return entity;
    }
}