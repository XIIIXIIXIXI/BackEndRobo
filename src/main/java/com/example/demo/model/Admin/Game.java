package com.example.demo.model.Admin;

import com.example.demo.model.Board;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public String name;
    public Integer gameId;
    //public Boolean started;
    public List<Integer> users = new ArrayList<>();

    public Board board;

    public boolean addUser(int userId){return users.add(userId);}
}
