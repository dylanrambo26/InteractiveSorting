package com.example.sortinggame.model;
import com.example.sortinggame.service.GameService;

import java.util.*;
public class GameState {

    private final int gameId;
    private final String algorithm;
    private final List<Integer> array;
    private int swapCount;
    private boolean isSorted;

    private int lastSwappedIndex = -1;

    private boolean isNewPass = true;

    private int currentPass = 0;

    public GameState(int gameId, String algorithm, List<Integer> array){
        this.gameId = gameId;
        this.algorithm = algorithm;
        this.array = array;
        this.swapCount = 0;
        this.isSorted = false;
    }

    //getters
    public int getGameId(){
        return gameId;
    }
    public String getAlgorithm(){
        return algorithm;
    }
    public List<Integer> getArray(){
        return array;
    }
    public int getSwapCount(){
        return swapCount;
    }
    public boolean isSorted(){
        return isSorted;
    }

    /*public int getLastSwappedIndex(){
        return lastSwappedIndex;
    }*/

    public boolean isNewPass(){
        return isNewPass;
    }

    public int getCurrentPass(){
        return currentPass;
    }
    //setters
    public void incrementSwaps() {
        this.swapCount++;
    }

    public void setSorted(boolean sorted) {
        this.isSorted = sorted;
    }
    public void setLastSwappedIndex(int index){
        this.lastSwappedIndex = index;
    }
    public void setNewPass(boolean newPass){
        this.isNewPass = newPass;
    }
    public void setCurrentPass(int currentPass){
        this.currentPass = currentPass;
    }
}
