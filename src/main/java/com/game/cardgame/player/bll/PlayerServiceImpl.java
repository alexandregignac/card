package com.game.cardgame.player.bll;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.game.cardgame.base.bll.ServiceImpl;
import com.game.cardgame.game.entity.Game;
import com.game.cardgame.player.dal.PlayerRepository;
import com.game.cardgame.player.dto.PlayerDto;
import com.game.cardgame.player.entity.Player;
import com.game.cardgame.player.interfaces.PlayerMapper;
import com.game.cardgame.player.interfaces.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl extends ServiceImpl<Player, PlayerDto> implements PlayerService {

    @PersistenceContext
	private EntityManager em;

    @Autowired
    public PlayerServiceImpl(PlayerMapper playerMapper, PlayerRepository playerRepository) {
        super(playerMapper, playerRepository);
    }

    @Override
    protected void updateEntityFromDto(Player entity, PlayerDto dto) {
        entity.setGame(em.getReference(Game.class, dto.getGameId()));
        entity.setName(dto.getName());
    }

    @Override
    public List<PlayerDto> getAllInGame(Long gameId) {
        return ((PlayerRepository) getDal()).findByGameId(gameId).stream()
            .map(entity -> getMapper().mapToDto(entity))
            .collect(Collectors.toList());
    }
}