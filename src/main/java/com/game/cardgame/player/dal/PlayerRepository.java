package com.game.cardgame.player.dal;

import java.util.List;

import com.game.cardgame.player.entity.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long>{
    List<Player> findByGameId(Long gameId);
}