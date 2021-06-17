package com.example.demo.gameadmin;

public class UserDto {
    private String name;
    private Integer userId;
    private Integer gameId;
    private Boolean alreadyInGame;
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
