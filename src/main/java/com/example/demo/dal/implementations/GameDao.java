package com.example.demo.dal.implementations;

import com.example.demo.dal.interfaces.IGameDao;
import com.example.demo.model.Admin.Game;
import com.example.demo.model.Board;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class GameDao implements IGameDao {

    static final HashMap<Integer, Game> games = new HashMap<>();
    static private int gameIdCounter = 0;

    @Override
    public int createGame(Game game) {
        if (game != null){
            Integer gameId = game.gameId;
            if (gameId != null) {
                return -1;
            }else{
                game.gameId = ++gameIdCounter;
                games.put(game.gameId, game);
                return game.gameId;
            }
        }
        return -1;
    }
    @Override
    public boolean deleteGame(int gameID){
        return games.remove(gameID) != null;
    }

    @Override
    public Game getGame(int gameId) {
        return games.get(gameId);
    }
    @Override
    public Collection<Game> getGames(){
        return games.values();
    }


}
