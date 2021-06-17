package com.example.demo.service.interfaces;

import com.example.demo.dal.interfaces.IBoardDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Admin.Game;
import com.example.demo.model.Board;
import com.example.demo.model.Player;

import java.util.Collection;
import java.util.List;


public interface IGameAdminService {

    //int createGame() throws DaoException;

    public Collection<Game> getGames() throws ServiceException, DaoException;

    int saveGame(Game game) throws ServiceException,    DaoException;

    boolean deleteGame(int gameId) throws ServiceException, DaoException;

    public void addUserToGame(int gameID, int userID);

    public Game getGame(int gameId);
}
