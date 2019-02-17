package com.game.cardgame.game.bll;

import com.game.cardgame.game.dal.GameRepository;
import com.game.cardgame.game.entity.Game;
import com.game.cardgame.game.interfaces.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    public Long createNewGame(){
        Game game = gameRepository.save(new Game());
        return game.getId();
    }
 
    public void deleteGame(Long GameId){
        gameRepository.deleteById(GameId);
    }
}