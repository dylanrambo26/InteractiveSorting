package com.example.sortinggame.model;

public class ActionRequest {

    private int gameID;
    private int pass;
    private int index1;
    private int index2;

    public ActionRequest(){

    }
    //Constructor
    public ActionRequest(int gameID, int pass, int index1, int index2){
        this.gameID = gameID;
        this.pass = pass;
        this.index1 = index1;
        this.index2 = index2;
    }

    //Getters
    public int getGameID(){
        return gameID;
    }
    public int getPass(){
        return pass;
    }
    public int getIndex1(){
        return index1;
    }
    public int getIndex2(){
        return index2;
    }

    //Setters
    public void setGameID(int gameID){
        this.gameID = gameID;
    }
    public void setPass(int pass){
        this.pass = pass;
    }
    public void setIndex1(int index1){
        this.index1 = index1;
    }
    public void setIndex2(int index2){
        this.index2 = index2;
    }
}
