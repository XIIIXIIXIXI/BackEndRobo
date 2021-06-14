package com.example.demo.service.implementations;

import com.example.demo.model.Admin.*;
import com.example.demo.dal.interfaces.IBoardDao;
import com.example.demo.dal.interfaces.IPlayerDao;
import com.example.demo.dal.interfaces.ISpaceDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Board;
import com.example.demo.model.Player;
import com.example.demo.model.Space;
import com.example.demo.service.interfaces.IGameAdminService;
import com.example.demo.service.interfaces.IGameService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameAdminService implements IGameAdminService {
    private final IBoardDao boardDao;

    public GameAdminService(IBoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public List<Game> getGames() throws ServiceException, DaoException {
        List<Game> result = new ArrayList<Game>();
        for (Board board : boardDao.getBoards()) {
            Game game = new Game();
            game.name = board.boardName;
            game.id = board.getGameId();
            result.add(game);
            game.started = board.getPlayersNumber() > 1;
            for (int i = 0; i < board.getPlayersNumber(); i++) {
                Player player = board.getPlayer(i);
                User user = new User();
                user.playerid = player.getPlayerId();
                user.playername = player.getName();
                game.users.add(user);
            }
        }
        return result;
    }
}


