package com.example.demo.gameadmin;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.MappingException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Admin.Game;
import com.example.demo.service.interfaces.IGameAdminService;
import com.example.demo.util.mapping.IDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class GameAdminController {
    private final IGameAdminService gameAdminService;
    private final IDtoMapper dtoMapper;

    public GameAdminController(IGameAdminService gameAdminService, IDtoMapper dtoMapper){
        this.gameAdminService = gameAdminService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/game")
    public ResponseEntity<Collection<Game>> getGames() throws ServiceException, MappingException, DaoException{
        Collection<Game> games = gameAdminService.getGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
    /*@PostMapping("/game/new")
    if (gameDTO.gameId != null){
        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }
    Game game = dtoMapper.convertToEntity(gameDTO);
    gameAdminService.saveGame(game);
    return new ResponseEntity<>(game.gameId, HttpStatus.OK);*/
    @PostMapping("/game/new")
    public ResponseEntity<Integer> createGame(@RequestBody GameDto gameDto) throws ServiceException, MappingException, DaoException{
        if (gameDto.gameId != null){
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
        Game game = dtoMapper.convertToEntity(gameDto);
        gameAdminService.saveGame(game);
        return new ResponseEntity<>(game.gameId, HttpStatus.OK);
    }

}
