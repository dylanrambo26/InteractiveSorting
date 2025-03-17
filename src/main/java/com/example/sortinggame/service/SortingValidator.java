package com.example.sortinggame.service;
import com.example.sortinggame.model.GameState;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SortingValidator {

    public static boolean isValidSwap(GameState gameState,int pass, int index1, int index2){
        switch (gameState.getAlgorithm().toLowerCase()){
            case "bubble_sort":
                return isValidBubbleSortSwap(gameState,pass, index1, index2);
            case "selection_sort":
                return isValidSelectionSortSwap(gameState,pass, index1, index2);
            /*case "insertion_sort":
                return isValidInsertionSortSwap(array, index1, index2);*/
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

        if(gameState.isFirstSelectionSortSwap() && currentIndex != 0 && currentPass == 0){
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
        gameState.setFirstSelectionSortSwap(false);
        gameState.setInvalidPass(false);
        gameState.setInvalidSwap(false);
        gameState.setCurrentPass(currentPass + 1);
        gameState.setMessage("Swap is Valid.");
        return true;
    }
}
