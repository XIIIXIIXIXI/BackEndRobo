package com.example.demo.service.interfaces;

import com.example.demo.dal.interfaces.IBoardDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Admin.Game;
import com.example.demo.model.Board;
import com.example.demo.model.Player;

import java.util.List;


public interface IGameAdminService {

    int createGame() throws DaoException;

    public List<Game> getGames() throws ServiceException, DaoException;
}
