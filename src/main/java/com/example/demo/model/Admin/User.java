package com.example.demo.model.Admin;

public class User {
    private String name;
    private Integer userId;
    private Integer gameId;
    private Boolean alreadyInGame;

    public User(String name, Integer userId, int gameID){
        this.name = name;
        this.userId = userId;
        this.gameId = gameID;
        this.alreadyInGame = false;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getUserId(){
        return this.userId;
    }
    public void setGameId(int gameId){
        this.gameId = gameId;
    }
    public int getGameId(){
        return this.gameId;
    }
    public boolean getalreadyInGame(){
        return this.alreadyInGame;
    }
    public void setAlreadyInGame(boolean alreadyInGame){
        this.alreadyInGame = alreadyInGame;
    }
}
