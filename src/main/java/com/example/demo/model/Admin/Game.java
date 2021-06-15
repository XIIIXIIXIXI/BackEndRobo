package com.example.demo.model.Admin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    public String name;
    public int id;
    public Boolean started;
    public List<User> users = new ArrayList<User>();

    public Game(int id, String gameName){
        this.name = gameName;
        this.id = id;
        users = new LinkedList<>();
    }
    public void setGameId(int id){
        this.id = id;
    }
    public int getGameId(){
        return this.id;
    }
}

