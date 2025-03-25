package com.example.sortinggame.service;
import com.example.sortinggame.model.GameState;
import org.springframework.stereotype.Component;

import java.util.*;

//TODO Refactor repeated code in the different swap methods
@Component
public class SortingValidator {

    public static boolean isValidSortParameters(GameState gameState, int pass, int index1, int index2){

        switch (gameState.getAlgorithm().toLowerCase()){
            case "bubble_sort":
                return isValidBubbleSortSwap(gameState,pass, index1, index2);
            case "selection_sort":
                return isValidSelectionSortSwap(gameState,pass, index1, index2);
            case "insertion_sort":
                return isValidInsertionSortInsert(gameState, pass, index1, index2);
            //TODO Implement other sorting algorithms later
            default:
                return false;  // Invalid algorithm
        }
    }

    private static boolean isValidBubbleSortSwap(GameState gameState,int pass, int index1, int index2){
        List<Integer> array = gameState.getArray();

        int currentPass = gameState.getCurrentPass();

        if(pass != currentPass){
            gameState.setInvalidPass(true);
            gameState.setMessage("Pass is not equal to the current pass.");
            return false;
        }

        if(Math.abs(index1 - index2) != 1){
            gameState.setInvalidSwap(true);
            gameState.setMessage("Indexes need to be adjacent in order to swap.");
            return false;
        }

        //Check if index1 is less than index2 to ensure a left to right movement
        if(index1 > index2){
            gameState.setInvalidSwap(true);
            gameState.setMessage("Index 2 needs to be less than index 1 since bubble-sort sorts from left to right.");
            return false;
        }

        //Check if the swap is swapping elements that are out of order only
        if(array.get(index1) <= array.get(index2)){
            gameState.setInvalidSwap(true);
            gameState.setMessage("Elements at given indexes are already in order. No need to swap.");
            return false;
        }

        if(index2 > array.size() - pass - 1){
            gameState.setInvalidSwap(true);
            gameState.setMessage("Index2 is already in the sorted part of the array.");
            return false;
        }
        //Check if swaps are being incremented on each pass and that each pass starts at index 0
        if(index2 == array.size() - pass - 1){
            gameState.setCurrentPass(currentPass + 1);
            //gameState.setLastSwappedIndex(-1);
        }
        gameState.setInvalidPass(false);
        gameState.setInvalidSwap(false);
        gameState.setMessage("Swap is Valid.");
        return true;
    }

    private static boolean isValidSelectionSortSwap(GameState gameState, int pass, int currentIndex, int minIndex){
        List<Integer> array = gameState.getArray();

        int currentPass = gameState.getCurrentPass();

        if(gameState.isFirstSwap() && currentIndex != 0 && currentPass == 0){
            gameState.setInvalidSwap(true);
            gameState.setMessage("Current Index must be 0 during the first pass");
            return false;
        }
        if(pass != currentPass){
            gameState.setInvalidPass(true);
            gameState.setMessage("Pass is not equal to the current pass.");
            return false;
        }
        //Check if index1 is less than index2 to ensure a left to right movement
        if(currentIndex > minIndex){
            gameState.setInvalidSwap(true);
            gameState.setMessage("Index 2 needs to be less than index 1 since insertion-sort sorts from left to right.");
            return false;
        }

        int expectedMin = currentPass;
        for(int i = currentPass + 1; i < array.size(); i++){
            if(array.get(i) < array.get(expectedMin)){
                expectedMin = i;
            }
        }

        if(currentIndex != currentPass){
            gameState.setInvalidSwap(true);
            gameState.setMessage("The current index must be the same as the current pass.");
            return false;
        }

        if(minIndex != expectedMin){
            gameState.setInvalidSwap(true);
            gameState.setMessage("Value at Min Index is not the minimum value inside the unsorted portion.");
            return false;
        }
        gameState.setFirstSwap(false);
        gameState.setInvalidPass(false);
        gameState.setInvalidSwap(false);
        gameState.setCurrentPass(currentPass + 1);
        gameState.setMessage("Swap is Valid.");
        return true;
    }

    private static boolean isValidInsertionSortInsert(GameState gameState, int pass, int sortedIndex, int nextElement){
        List<Integer> array = gameState.getArray();

        int currentPass = gameState.getCurrentPass();
        int numInserts = gameState.getNumInserts();

        if(gameState.isFirstSwap() && sortedIndex != 0 && currentPass == 0){
            gameState.setMessage("Sorted Index must start at 0 on first pass");
            return false;
        }
        if(pass != currentPass){
            gameState.setInvalidPass(true);
            gameState.setMessage("Pass is not equal to the current pass.");
            return false;
        }
        if(sortedIndex != numInserts){
            gameState.setMessage("Sorted Index does not match end of sorted part.");
            return false;
        }
        if(Math.abs(sortedIndex - nextElement) != 1){
            gameState.setMessage("Next Element must be the next element in the unsorted array.");
            return false;
        }
        if(sortedIndex > nextElement){
            gameState.setMessage("Next Element index needs to be larger than sorted index.");
            return false;
        }
        gameState.setFirstSwap(false);
        gameState.setInvalidPass(false);
        gameState.setCurrentPass(currentPass + 1);
        gameState.setNumInserts(numInserts + 1);
        gameState.setMessage("Insertion Valid");
        return true;

    }
}
