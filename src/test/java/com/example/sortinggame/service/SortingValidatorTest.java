package com.example.sortinggame.service;

import com.example.sortinggame.model.GameState;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class SortingValidatorTest {

    @Test
    void testValidBubbleSort(){
        GameState gameState = new GameState(1, "Bubble_Sort", Arrays.asList(5, 3, 4, 1));
        gameState.setCurrentPass(0);
        assertTrue(SortingValidator.isValidSwap(gameState,0,0,1));
        Collections.swap(gameState.getArray(), 0,1);
        assertTrue(SortingValidator.isValidSwap(gameState,0,1,2));
        Collections.swap(gameState.getArray(), 1,2);
        //assertTrue(SortingValidator.isValidSwap(gameState,1,1,2));
        //assertFalse(SortingValidator.isValidSwap(gameState, 1,3,1));
    }

    @Test
    void testInvalidBubbleSort(){
        GameState gameState = new GameState(1, "Bubble_Sort", Arrays.asList(5, 3, 4, 1));
        gameState.setCurrentPass(0);
        assertFalse(SortingValidator.isValidSwap(gameState,0,0,2));
        assertFalse(SortingValidator.isValidSwap(gameState,0,2,1));
        assertFalse(SortingValidator.isValidSwap(gameState, 1, 0, 1));
    }

    @Test
    void testBubbleSortAlgorithm(){
        List<Integer> array = Arrays.asList(5,3,4,1);
        GameState gameState = new GameState(1, "Bubble_Sort", array);
        for(int pass = 0; pass < array.size(); pass++){
            for(int j = 0; j < array.size() - pass - 1; j++){
                if(gameState.getArray().get(j) > gameState.getArray().get(j + 1)){
                    assertTrue(SortingValidator.isValidSwap(gameState, pass, j, j + 1));
                    Collections.swap(array, j, j + 1);
                }
            }
        }
    }
}
