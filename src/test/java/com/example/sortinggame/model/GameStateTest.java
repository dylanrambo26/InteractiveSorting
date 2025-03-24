package com.example.sortinggame.model;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {
    /*@Test
    void testInitialGameState(){
        List<Integer> initialArray = Arrays.asList(5,3,4,1);
        GameState gameState = new GameState(1,"BubbleSort", initialArray);
        assertEquals(1, gameState.getGameId());
        assertEquals("BubbleSort", gameState.getAlgorithm());
        assertEquals(initialArray, gameState.getArray());
        assertEquals(0, gameState.getSwapCount());
        assertFalse(gameState.isSorted());
        assertEquals(0, gameState.getCurrentPass());
    }

    @Test
    void testIncrementSwaps(){
        GameState gameState = new GameState(1,"BubbleSort", Arrays.asList(5,3,4,1));
        gameState.incrementSwaps();
        assertEquals(1, gameState.getSwapCount());
    }

    @Test
    void testPassTracking() {
        GameState gameState = new GameState(1, "BubbleSort", Arrays.asList(5, 3, 4, 1));
        gameState.setCurrentPass(1);
        assertEquals(1, gameState.getCurrentPass());
    }*/
}
