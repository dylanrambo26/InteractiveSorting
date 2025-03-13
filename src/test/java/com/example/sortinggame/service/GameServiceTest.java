package com.example.sortinggame.service;

import com.example.sortinggame.model.GameState;
import com.example.sortinggame.model.SwapRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class GameServiceTest {

    private GameService gameService;
    private GameState gameState;

    //Initializes the gameService and gameState for every test method
    @BeforeEach
    void setUp(){
        gameService = new GameService();
        gameState = gameService.startGame("bubble_sort");
    }

    //Test SwapRequests for the invalid tests
    static Stream<SwapRequest> invalidSwapRequestStream(){
        return Stream.of(
                new SwapRequest(1,0,2,1),
                new SwapRequest(1,0,1,3),
                new SwapRequest(1,1,0,1),
                new SwapRequest(1,0,1,1),
                new SwapRequest(1,0,1,2)
        );
    }

    //Tests a valid user swapRequest
    @Test
    void testApplyValidSwap(){
        SwapRequest swapRequest = new SwapRequest(1, 0,0,1);
        List<Integer> expectedArray = gameState.getArray();
        GameState updatedGameState = gameService.processSwap(swapRequest);
        Collections.swap(expectedArray, swapRequest.getIndex1(),swapRequest.getIndex2());
        assertEquals(expectedArray, updatedGameState.getArray());
    }

    //Tests multiple incorrect user swapRequests
    @ParameterizedTest
    @MethodSource("invalidSwapRequestStream")
    void testInvalidSwap(SwapRequest swapRequest){
        GameState updatedGameState = gameService.processSwap(swapRequest);
        assertEquals(gameState.getArray(), updatedGameState.getArray());
    }


    //Tests the processSwap() method inside GameService with the actual bubble sort algorithm
    @Test
    void testBubbleSortAlgorithm(){
        SwapRequest swapRequest;
        List<Integer> expectedArray = new ArrayList<>(gameState.getArray());
        for(int pass = 0; pass < gameState.getArray().size(); pass++){
            for(int j = 0; j < gameState.getArray().size() - pass - 1; j++){
                if(gameState.getArray().get(j) > gameState.getArray().get(j + 1)){
                    swapRequest = new SwapRequest(1, pass, j, j + 1);
                    gameState = gameService.processSwap(swapRequest);
                    Collections.swap(expectedArray, j, j + 1);
                    assertEquals(gameState.getArray(), expectedArray);
                }
            }
        }
    }
}
