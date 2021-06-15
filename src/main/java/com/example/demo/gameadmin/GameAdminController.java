package com.example.demo.gameadmin;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.MappingException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Admin.Game;
import com.example.demo.service.implementations.GameService;
import com.example.demo.service.interfaces.IGameAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class GameAdminController {
    private final IGameAdminService gameAdminService;
    private final GameService gameService;

    public GameAdminController(IGameAdminService gameAdminService, GameService gameService){
        this.gameAdminService = gameAdminService;
        this.gameService = gameService;
    }

    /*@GetMapping("/game")
    public ResponseEntity<List<Game>> getGames() throws ServiceException, MappingException, DaoException{
        List<Game> games = gameAdminService.getGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }*/
    @PostMapping("/game/new")
    public ResponseEntity<Long> createGame() throws ServiceException, DaoException{
        long gameId = gameAdminService.createGame();
        return new ResponseEntity<>(gameId, HttpStatus.CREATED);
    }
}
