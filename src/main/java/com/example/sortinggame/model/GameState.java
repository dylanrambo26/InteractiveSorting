package com.example.sortinggame.model;
import com.example.sortinggame.service.GameService;

import java.util.*;

public class GameState {

    private boolean isInvalidSwap;
    private boolean isInvalidPass;
    private String message;
    private final int gameId;
    private final String algorithm;
    private final List<Integer> array;
    //private List<List<Integer>> mergeSortSubarrays;
    private List<GameService.Partition> partitionList;
    private int swapCount;
    private boolean isSorted;

    private int lastSwappedIndex = -1;

    private boolean isNewPass = true;

    private int currentPass = 0;
    private boolean isFirstSwap = true;

    private int numInserts = 0;

    private int numPartitions;

    public GameState(int gameId, String algorithm, List<Integer> array){
        this.gameId = gameId;
        this.algorithm = algorithm;
        this.array = array;
        this.swapCount = 0;
        this.isSorted = false;
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
    public int getNumPartitions(){
        return numPartitions;
    }
    /*public List<List<Integer>> getMergeSortSubarrays(){
        return mergeSortSubarrays;
    }*/

    public List<GameService.Partition> getPartitionList(){
        return partitionList;
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
    public void setInvalidSwap(boolean invalidSwap){this.isInvalidSwap = invalidSwap;}
    public void setInvalidPass(boolean invalidPass){this.isInvalidPass = invalidPass;}
    public void setMessage(String message){this.message = message;}
    public void setFirstSwap(boolean firstSwap){
        this.isFirstSwap = firstSwap;
    }
    public void setNumInserts(int numInserts){
        this.numInserts = numInserts;
    }
    public void setNumPartitions(int numPartitions){
        this.numPartitions = numPartitions;
    }
    public void setPartitionList(List<GameService.Partition> partitionList){
        this.partitionList = partitionList;
    }
}
