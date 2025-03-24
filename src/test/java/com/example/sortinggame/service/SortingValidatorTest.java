package com.example.sortinggame.service;

import com.example.sortinggame.model.GameState;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class SortingValidatorTest {
/*
    @Test
    void testValidBubbleSort(){
        GameState gameState = new GameState(1, "Bubble_Sort", Arrays.asList(5, 3, 4, 1));
        gameState.setCurrentPass(0);
        assertTrue(SortingValidator.isValidSortParameters(gameState,0,0,1));
        Collections.swap(gameState.getArray(), 0,1);
        assertTrue(SortingValidator.isValidSortParameters(gameState,0,1,2));
        Collections.swap(gameState.getArray(), 1,2);
        //assertTrue(SortingValidator.isValidSwap(gameState,1,1,2));
        //assertFalse(SortingValidator.isValidSwap(gameState, 1,3,1));
    }

    @Test
    void testInvalidBubbleSort(){
        GameState gameState = new GameState(1, "Bubble_Sort", Arrays.asList(5, 3, 4, 1));
        gameState.setCurrentPass(0);
        assertFalse(SortingValidator.isValidSortParameters(gameState,0,0,2));
        assertFalse(SortingValidator.isValidSortParameters(gameState,0,2,1));
        assertFalse(SortingValidator.isValidSortParameters(gameState, 1, 0, 1));
    }

    @Test
    void testBubbleSortAlgorithm(){
        List<Integer> array = Arrays.asList(5,3,4,1);
        GameState gameState = new GameState(1, "Bubble_Sort", array);
        for(int pass = 0; pass < array.size(); pass++){
            for(int j = 0; j < array.size() - pass - 1; j++){
                if(gameState.getArray().get(j) > gameState.getArray().get(j + 1)){
                    assertTrue(SortingValidator.isValidSortParameters(gameState, pass, j, j + 1));
                    Collections.swap(array, j, j + 1);
                }
            }
        }
    }

    //Tests first invalid inputs after initialization
    @Test
    void testInvalidSelectionSortInitial(){
        GameState gameState = new GameState(1, "selection_sort", Arrays.asList(5,4,1,3,2));
        assertFalse(SortingValidator.isValidSortParameters(gameState,1,0, 2));
        assertFalse(SortingValidator.isValidSortParameters(gameState,0,0,1));
    }

    @Test
    void testSelectionSortAlgorithm(){
        List<Integer> array = Arrays.asList(64,25,12,22,11);
        GameState gameState = new GameState(1,"selection_sort", array);
        int minIndex;
        for(int i = 0; i < array.size() - 1; i++){
            minIndex = i;
            for(int j = i + 1; j < array.size(); j++){
                if(array.get(j) < array.get(minIndex)){
                    minIndex = j;
                }
            }
            assertTrue(SortingValidator.isValidSortParameters(gameState, i, i, minIndex));
            Collections.swap(array, i, minIndex);
        }
    }

    @Test
    void testInsertionSortInitial(){
        GameState gameState = new GameState(1,"insertion_sort",Arrays.asList(5,4,1,3,2));
        assertFalse(SortingValidator.isValidSortParameters(gameState, 1,0,1));
        assertFalse(SortingValidator.isValidSortParameters(gameState,0,1,2));
        assertFalse(SortingValidator.isValidSortParameters(gameState,0,2,1));
        assertFalse(SortingValidator.isValidSortParameters(gameState,0,0,2));
    }

    @Test
    void testInsertionSortAlgorithm(){
        List<Integer> array = Arrays.asList(5,4,1,3,2);
        GameState gameState = new GameState(1,"insertion_sort",array);

        int n = array.size();
        for(int nextElement = 1; nextElement < n; nextElement++){
            int key = array.get(nextElement);
            int j = nextElement - 1;
            assertTrue(SortingValidator.isValidSortParameters(gameState,j,j,nextElement));
            while (j >= 0 && array.get(j) > key) {
                array.set(j + 1, array.get(j));
                j = j - 1;
            }
            array.set(j + 1, key);
        }
    }*/
}
