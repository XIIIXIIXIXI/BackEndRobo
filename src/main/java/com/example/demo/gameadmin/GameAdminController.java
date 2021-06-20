package com.example.demo.gameadmin;

import com.example.demo.controller.GameController.SpaceDto;
import com.example.demo.dal.implementations.BoardDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.MappingException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Admin.Game;
import com.example.demo.model.Admin.User;
import com.example.demo.model.Board;
import com.example.demo.model.Player;
import com.example.demo.model.Space;
import com.example.demo.service.implementations.GameService;
import com.example.demo.service.interfaces.IGameAdminService;
import com.example.demo.service.interfaces.IUserService;
import com.example.demo.util.mapping.IDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "*")
public class GameAdminController {
    private final IGameAdminService gameAdminService;
    private final IDtoMapper dtoMapper;
    private final IUserService userService;
    private final GameService gameService;

    public GameAdminController(IGameAdminService gameAdminService, IDtoMapper dtoMapper, IUserService userService, GameService gameService){
        this.gameAdminService = gameAdminService;
        this.dtoMapper = dtoMapper;
        this.userService = userService;
        this.gameService = gameService;
    }

    @GetMapping("/game")
    public ResponseEntity<Collection<Game>> getGames() throws ServiceException, MappingException, DaoException{
        Collection<Game> games = gameAdminService.getGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
    //her bruger vi pathvariable som indikerer at URL'en er attributen vi vil arbejde med.
    //ellers brug RequestBody hvis vi vil have det sendt med Postman.
    @DeleteMapping("/game/{gameId}/delete")
     public ResponseEntity<Boolean> deleteGame(@PathVariable("gameId") int gameId) throws ServiceException, MappingException, DaoException{
     boolean returning = gameAdminService.deleteGame(gameId);
     return new ResponseEntity<>(returning, HttpStatus.OK);
    }


   @PostMapping("/game/{gameId}/usert")
   public ResponseEntity<String> addPlayer(@PathVariable("gameId") int gameId) throws ServiceException, MappingException, DaoException {
        int userId = userService.createUser();
        User user = userService.getUser(userId);
        gameAdminService.addUserToGame(gameId, userId);
       //System.out.println(gameService.getBoard(gameId)+ " = board, " + user.getName() +" = username");
        user.setName(userId+"");
        Player player = new Player(gameService.getBoard(gameId), "yellow", user.getName());
        player.setPlayerId(user.getUserId());
        gameService.addPlayer(/*gameService.getBoard(gameId).getGameId()*/gameId, player);
        //gameService.movePlayer(gameService.getBoard(gameId),3,3,usert);
        return new ResponseEntity<String>("User: "+user.getUserId()+" has been added to: "+gameId, HttpStatus.OK);

   }
    @PostMapping("/game/new")
    public ResponseEntity<Integer> createGame() throws ServiceException, MappingException, DaoException{
        int gameId = gameService.createGame();
        Game game = gameAdminService.getGame(gameId);
        gameService.deleteBoard(gameId);
        Board board = new Board(gameId, 8, 8, "Board");
        gameService.saveBoard(board);
        game.setBoard(board);
        //gameService.saveBoard(board);
        return new ResponseEntity<>(gameId, HttpStatus.CREATED);
    }
    //adds board to a game
    @PostMapping("/game/{gameId}/newBoard")
    public ResponseEntity<Integer> createBoard(@PathVariable("gameId") int gameId) throws ServiceException, DaoException{
        Game game = gameAdminService.getGame(gameId);
        gameService.deleteBoard((int)gameId);
        Board board = new Board(gameId,8,8, "gamename");
        board.setGameId(gameId);
        gameService.saveBoard(board);
        game.board = board;
        return new ResponseEntity<>(game.gameId, HttpStatus.OK);

    }
    @PostMapping("/game/get/{id}")
    public ResponseEntity<GameDto> getGame(@PathVariable("gameId") int gameId) throws ServiceException, MappingException, DaoException {
        Game game = gameAdminService.getGame(gameId);
        if(game == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(dtoMapper.convertToDto(game), HttpStatus.OK);
    }


    /**
     * Move the current player
     *
     * @param gameId  the board on which we want to move the current player
     * @param spaceDto the space we want to move the player upon
     * @return Staus code indicating whether it went well or not
     */
    @PutMapping("/game/get/{gameId}/board/move")
    public ResponseEntity<Void> moveCurrentPlayer(@PathVariable("gameId") int gameId, @RequestBody SpaceDto spaceDto) throws ServiceException, DaoException {
        Game game = gameAdminService.getGame(gameId);
        if (game == null /*|| !game..isStarted()*/) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        Board board = gameService.getBoard(gameId);
        Space space = dtoMapper.convertToEntity(spaceDto, board);
        gameService.moveCurrentPlayer(gameId, space.x, space.y);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Set the current player
     *
     * @param gameId  the board we are working upon
     * @param playerId the player we want to set as the current player
     * @return nothing
     */
    @PutMapping("/game/get/{gameId}/board/currentPlayer/{playerId}")
    public ResponseEntity<Void> setCurrentPlayer(@PathVariable("gameId") int gameId, @PathVariable("playerId") int playerId) throws ServiceException, DaoException {
        gameService.setCurrentPlayer(gameId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
