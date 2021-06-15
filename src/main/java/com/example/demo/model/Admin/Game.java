package com.example.demo.model.Admin;

import com.example.demo.model.Board;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public String name;
    public Integer gameId;
    //public Boolean started;
    public List<User> users = new ArrayList<User>();

    public Board board;
}
