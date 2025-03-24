package com.example.sortinggame.model;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class GameState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameId;
    private String algorithm;
    @ElementCollection
    @CollectionTable(
            name = "game_state_array",
            joinColumns = @JoinColumn(name = "game_id")
    )
    @Column(name = "array_value")
    private List<Integer> array = new ArrayList<>();
    private boolean isInvalidSwap;
    private boolean isInvalidPass;
    private String message;
    private int swapCount = 0;
    private boolean isSorted;
    private boolean isNewPass = true;
    private int currentPass = 0;
    private boolean isFirstSwap = true;

    private int numInserts = 0;

    //Default Constructor for JPA
    public GameState(){}
    public GameState(String algorithm, List<Integer> array){
        this.algorithm = algorithm;
        this.array = array;
        this.message = "Select two indexes to get started";
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

    public boolean isNewPass(){
        return isNewPass;
    }

    public boolean isInvalidSwap(){
        return isInvalidSwap;
    }

    public boolean isInvalidPass(){
        return isInvalidPass;
    }
    public int getCurrentPass(){
        return currentPass;
    }

    public String getMessage(){
        return message;
    }
    public boolean isFirstSwap(){
        return isFirstSwap;
    }
    public int getNumInserts(){
        return numInserts;
    }
    //setters
    public void incrementSwaps() {
        this.swapCount++;
    }

    public void setSorted(boolean sorted) {
        this.isSorted = sorted;
    }
    /*public void setLastSwappedIndex(int index){
        this.lastSwappedIndex = index;
    }*/
    public void setNewPass(boolean newPass){
        this.isNewPass = newPass;
    }
    public void setCurrentPass(int currentPass){
        this.currentPass = currentPass;
    }
    public void setInvalidSwap(boolean invalidSwap){this.isInvalidSwap = invalidSwap;}
    public void setInvalidPass(boolean invalidPass){this.isInvalidPass = invalidPass;}
    public void setMessage(String message){this.message = message;}
    public void setFirstSwap(boolean firstSwap){
        this.isFirstSwap = firstSwap;
    }
    public void setNumInserts(int numInserts){
        this.numInserts = numInserts;
    }
}
