package com.example.demo.dal.implementations;

import com.example.demo.dal.interfaces.IGameDao;
import com.example.demo.model.Admin.Game;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Random;

@Repository
public class GameDao implements IGameDao {
    //used for getting an unique game id
    Random ran = new Random();
    static final HashMap<Integer, Game> games = new HashMap<>();

    public int createGame(Game game){
        game.setGameId(ran.nextInt());
        games.put(game.getGameId(), game);
        return game.getGameId();
    }
}

