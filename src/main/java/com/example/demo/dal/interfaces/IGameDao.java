package com.example.demo.dal.interfaces;

import com.example.demo.model.Admin.Game;

import java.util.Collection;
import java.util.List;

public interface IGameDao {
    int createGame(Game game);

    Game getGame(int gameId);

    Collection<Game> getGames();
}
