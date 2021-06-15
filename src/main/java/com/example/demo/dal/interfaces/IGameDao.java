package com.example.demo.dal.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.model.Admin.Game;
import com.example.demo.model.Board;

import java.util.Collection;


public interface IGameDao {
    int createGame(Game game) throws DaoException;
}
