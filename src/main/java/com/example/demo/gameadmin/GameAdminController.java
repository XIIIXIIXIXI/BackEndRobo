package com.example.demo.gameadmin;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.MappingException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Admin.Game;
import com.example.demo.service.implementations.GameAdminService;
import com.example.demo.service.interfaces.IGameAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class GameAdminController {
    private final IGameAdminService gameAdminService;

    public GameAdminController(IGameAdminService gameAdminService){
        this.gameAdminService = gameAdminService;
    }

    @GetMapping("/game")
    public ResponseEntity<List<Game>> getGames() throws ServiceException, MappingException, DaoException{
        List<Game> games = gameAdminService.getGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

}
