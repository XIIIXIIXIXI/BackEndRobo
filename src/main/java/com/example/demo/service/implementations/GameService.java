package com.example.demo.service.implementations;

import com.example.demo.dal.interfaces.IBoardDao;
import com.example.demo.dal.interfaces.IGameDao;
import com.example.demo.dal.interfaces.IPlayerDao;
import com.example.demo.dal.interfaces.ISpaceDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Admin.Game;
import com.example.demo.model.Board;
import com.example.demo.model.Player;
import com.example.demo.model.Space;
import com.example.demo.service.interfaces.IGameService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GameService implements IGameService {
    private final IBoardDao boardDao;
    private final ISpaceDao spaceDao;
    private final IPlayerDao playerDao;
    private final IGameDao gameDao;
    private final GameAdminService gameAdminService;

    public GameService(IBoardDao boardDao, ISpaceDao spaceDao, IPlayerDao playerDao, IGameDao gameDao, GameAdminService gameAdminService) {
        this.boardDao = boardDao;
        this.spaceDao = spaceDao;
        this.playerDao = playerDao;
        this.gameDao = gameDao;
        this.gameAdminService = gameAdminService;
    }

    @Override
    public Board getBoard(int boardId) throws ServiceException, DaoException {
        if (boardId < 0) {
            throw new ServiceException("Invalid board id " + boardId, HttpStatus.BAD_REQUEST);
        }
        Board board = boardDao.getBoard(boardId);
        if (board == null) {
            throw new ServiceException("No board found with board id " + boardId, HttpStatus.NOT_FOUND);
        }

        return board;
    }

    @Override
    public int saveBoard(Board board) throws ServiceException, DaoException {
        int savedBoardId = boardDao.createBoard(board);
        spaceDao.createSpaces(savedBoardId, board.getSpaces());
        return savedBoardId;
    }

    @Override
    public Player getCurrentPlayer(int boardId) throws ServiceException, DaoException {
        if (boardId < 0) {
            throw new ServiceException("Invalid board id " + boardId, HttpStatus.BAD_REQUEST);
        }
        Board board = boardDao.getBoard(boardId);
        if (board == null) {
            throw new ServiceException("No board found for board id " + boardId, HttpStatus.NOT_FOUND);
        }
        Player player = board.getCurrentPlayer();
        if (player == null) {
            throw new ServiceException("The board with id " + boardId + " has no current player", HttpStatus.NOT_FOUND);
        }
        return player;
    }

    @Override
    public void setCurrentPlayer(int boardId, int playerId) throws ServiceException, DaoException {
        if (boardId < 0) {
            throw new ServiceException("Invalid board id " + boardId, HttpStatus.BAD_REQUEST);
        }
        if (playerId < 0) {
            throw new ServiceException("Invalid player id " + playerId, HttpStatus.BAD_REQUEST);
        }
        Board board = boardDao.getBoard(boardId);
        if (board == null) {
            throw new ServiceException("No board found for board id " + boardId, HttpStatus.NOT_FOUND);
        }
        Player player = playerDao.getPlayer(playerId);
        if (player == null) {
            throw new ServiceException("No player found for player id " + playerId, HttpStatus.NOT_FOUND);
        }
        board.setCurrentPlayer(player);
        boardDao.updateBoard(board, board.getGameId());
    }

    @Override
    public int addPlayer(int boardId, Player player) throws ServiceException, DaoException {
        if (player == null) {
            throw new ServiceException("Player to add to board was null", HttpStatus.BAD_REQUEST);
        }
        Board board = this.getBoard(boardId);
        int playerId = playerDao.addPlayer(boardId, player);
        board.addPlayer(player);
        boardDao.updateBoard(board, board.getGameId());
        return playerId;
    }

    @Override
    public int createGame() throws ServiceException, DaoException {
        Game game = new Game(null, "Default Game");
        int id = gameDao.createGame(game);
        return id;
    }

    @Override
    public void moveCurrentPlayer(int boardId, int x, int y) throws ServiceException, DaoException {
        Board board = this.getBoard(boardId);
        Player currentPlayer = board.getCurrentPlayer();
        if (currentPlayer == null) {
            throw new ServiceException("The board " + boardId + " has no current player", HttpStatus.BAD_REQUEST);
        }
        if (x < 0 || y < 0 || x > board.height || y > board.width) {
            throw new ServiceException("Space coordinates (" + x + "," + y + ") were invalid for board" + boardId, HttpStatus.BAD_REQUEST);
        }
        Space targetSpace = board.getSpace(x, y);
        if (targetSpace == null) {
            throw new ServiceException("Provided target space was not found", HttpStatus.NOT_FOUND);
        }
        currentPlayer.setSpace(targetSpace);
        boardDao.updateBoard(board, board.getGameId());
    }

    @Override
    public void movePlayer(Board board, int x, int y, int playerId) throws DaoException {
        Space space = board.getSpace(x, y);
        Player player = board.getPlayerById(playerId);
        if (space != null && player != null) {
            player.setSpace(space);
            boardDao.updateBoard(board, board.getGameId());
            System.out.println("rykket");
        } else {
            System.out.println("bugged" + player  + space);
        }

    }

    @Override
    public void switchCurrentPlayer(int boardId) throws ServiceException, DaoException {
        Board board = this.getBoard(boardId);
        if(board.getCurrentPlayer() !=null) {
            int amountOfPlayers = board.getPlayersNumber();
            if (amountOfPlayers <= 0) {
                throw new ServiceException("Trying to switch current player, but board has no players", HttpStatus.BAD_REQUEST);
            }

            int currentPlayerNumber = board.getPlayerNumber(board.getCurrentPlayer());
            int nextPlayerNumber;
            if (amountOfPlayers > 1) {
                nextPlayerNumber = (currentPlayerNumber + 1) % amountOfPlayers;
            } else {
                nextPlayerNumber = 1;
            }
            board.setCurrentPlayer(board.getPlayer(nextPlayerNumber));
            boardDao.updateBoard(board, board.getGameId());
        }
    }

    @Override
    public  void deleteBoard(int gameId) throws ServiceException, DaoException{

        boardDao.deleteBoard(gameId);

    }
}
