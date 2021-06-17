package com.example.demo.gameadmin;

import com.example.demo.dal.implementations.BoardDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.MappingException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Admin.Game;
import com.example.demo.model.Admin.User;
import com.example.demo.model.Board;
import com.example.demo.model.Player;
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

    @PostMapping("/account/new")
    public ResponseEntity<Integer> createUser() throws ServiceException, DaoException{
    int userId = userService.createUser();
    return new ResponseEntity<>(userId, HttpStatus.OK);
    }
   /* @PostMapping("/game/addUser/{gameID}")
    public ResponseEntity<Boolean> addUser(@RequestBody int UserID, @PathVariable("gameID") int gameID){
        User user =
    }*/
   @PostMapping("/game/{gameId}/{usert}")
   public ResponseEntity<Integer> addPlayer(@PathVariable("gameId") int gameId, @PathVariable("usert") int usert) throws ServiceException, MappingException, DaoException {
       User user = userService.getUser(usert);
        gameAdminService.addUserToGame(gameId, usert);

        Player player = new Player(gameService.getBoard(gameId), "yellow", user.getName());
        player.setPlayerId(user.getUserId());
        gameService.addPlayer(gameService.getBoard(gameId).getGameId(), player);
        return new ResponseEntity<>(user.getUserId(), HttpStatus.OK);

   }
    @PostMapping("/game/new")
    public ResponseEntity<Integer> createGame(@RequestBody GameDto gameDto) throws ServiceException, MappingException, DaoException{
        /*if (gameDto.gameId != null){
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }*/

        Game game = dtoMapper.convertToEntity(gameDto);
        game.gameId = 4;
        Board board = gameService.getBoard(1);
        User user1 = new User("User1", 1, game.gameId);
        User user2 = new User("User2", 2, game.gameId);
        game.setBoard(board);
        game.addUser(user1);
        game.addUser(user2);
        gameAdminService.saveGame(game);
        return new ResponseEntity<>(game.gameId, HttpStatus.OK);
    }

}
