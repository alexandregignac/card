package com.game.cardgame.player.interfaces;

import java.util.List;

import com.game.cardgame.player.dto.PlayerDto;

public interface PlayerService {

    public PlayerDto get(Long playerId);
    
    public void update(PlayerDto player);

    public PlayerDto create(PlayerDto player);
    
    public void delete(Long playerId);

    public List<PlayerDto> getAllInGame(Long gameId);
}