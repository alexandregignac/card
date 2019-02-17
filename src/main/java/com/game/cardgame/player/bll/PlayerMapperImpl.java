package com.game.cardgame.player.bll;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.game.cardgame.base.bll.MapperImpl;
import com.game.cardgame.player.dto.PlayerDto;
import com.game.cardgame.player.entity.Player;
import com.game.cardgame.player.interfaces.PlayerMapper;
import com.game.cardgame.game.entity.Game;

import org.springframework.stereotype.Component;

@Component
public class PlayerMapperImpl extends MapperImpl<Player, PlayerDto> implements PlayerMapper {
        
    @PersistenceContext
    private EntityManager em;
    
    public PlayerMapperImpl() {
        super(Player::new, PlayerDto::new);
    }
    
    @Override
    public PlayerDto mapToDto(Player entity) {
        PlayerDto dto = super.mapToDto(entity);
        dto.setGameId(entity.getGame().getId());
        return dto;
    }

    @Override
    public Player mapToEntity(PlayerDto dto) {
        Player entity = super.mapToEntity(dto);
        entity.setGame(em.getReference(Game.class, dto.getGameId()));
        return entity;
    }
}