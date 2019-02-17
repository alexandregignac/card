package com.game.cardgame.deck.bll;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.game.cardgame.base.bll.ServiceImpl;
import com.game.cardgame.deck.dal.DeckRepository;
import com.game.cardgame.deck.dto.DeckDto;
import com.game.cardgame.deck.entity.Deck;
import com.game.cardgame.deck.interfaces.DeckMapper;
import com.game.cardgame.deck.interfaces.DeckService;
import com.game.cardgame.game.entity.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeckServiceImpl extends ServiceImpl<Deck, DeckDto>  implements DeckService {

    @PersistenceContext
	private EntityManager em;

    @Autowired
    public DeckServiceImpl(DeckMapper deckMapper, DeckRepository deckRepository) {
        super(deckMapper, deckRepository);
    }

    @Override
    protected void updateEntityFromDto(Deck entity, DeckDto dto) {
        entity.setGame(em.getReference(Game.class, dto.getGameId()));
        entity.setShoeOrder(dto.getShoeOrder());
    }

    public Integer numbersOfDeckInShoe(Long gameId){
        return ((DeckRepository) getDal()).countByGameId(gameId);
    }

    public DeckDto getDeckInPlay(Long gameId){
        Deck deck = ((DeckRepository) getDal()).findTopByGameIdOrderByShoeOrder(gameId);
        return getMapper().mapToDto(deck); 
    }
}