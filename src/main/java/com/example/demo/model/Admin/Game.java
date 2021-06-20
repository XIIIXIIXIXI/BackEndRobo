package com.example.demo.model.Admin;

import com.example.demo.model.Board;

import java.util.LinkedList;
import java.util.List;

public class Game {
    public String name;
    public Integer gameId;
    //public Boolean started;
    public List<User> users;

    public Board board;

    public boolean addUser(User user){return users.add(user);}

    public void setBoard(Board board) {
        this.board = board;
    }

    public Game(Integer gameId, String gameName) {
        this.gameId = gameId;
        this.name = gameName;
        users = new LinkedList<>();
    }
}
