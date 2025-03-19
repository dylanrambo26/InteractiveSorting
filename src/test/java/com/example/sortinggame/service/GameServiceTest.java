package com.example.sortinggame.service;

import com.example.sortinggame.model.GameState;
import com.example.sortinggame.model.StartRequest;
import com.example.sortinggame.model.ActionRequest;
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
        gameState = gameService.startGame(new StartRequest("bubble_sort", 5));
    }

    //Test SwapRequests for the invalid tests
    static Stream<ActionRequest> invalidSwapRequestStream(){
        return Stream.of(
                new ActionRequest(1,0,2,1),
                new ActionRequest(1,0,1,3),
                new ActionRequest(1,1,0,1),
                new ActionRequest(1,0,1,1),
                new ActionRequest(1,0,1,2)
        );
    }

    //Tests a valid user swapRequest
    @Test
    void testApplyValidSwap(){
        ActionRequest actionRequest = new ActionRequest(1, 0,0,1);
        List<Integer> expectedArray = gameState.getArray();
        GameState updatedGameState = gameService.processSwap(actionRequest);
        Collections.swap(expectedArray, actionRequest.getIndex1(), actionRequest.getIndex2());
        assertEquals(expectedArray, updatedGameState.getArray());
    }

    //Tests multiple incorrect user swapRequests
    @ParameterizedTest
    @MethodSource("invalidSwapRequestStream")
    void testInvalidSwap(ActionRequest actionRequest){
        GameState updatedGameState = gameService.processSwap(actionRequest);
        assertEquals(gameState.getArray(), updatedGameState.getArray());
    }


    //Tests the processSwap() method inside GameService with the actual bubble sort algorithm
    @Test
    void testBubbleSortAlgorithm(){
        ActionRequest actionRequest;
        List<Integer> expectedArray = new ArrayList<>(gameState.getArray());
        for(int pass = 0; pass < gameState.getArray().size(); pass++){
            for(int j = 0; j < gameState.getArray().size() - pass - 1; j++){
                if(gameState.getArray().get(j) > gameState.getArray().get(j + 1)){
                    actionRequest = new ActionRequest(1, pass, j, j + 1);
                    gameState = gameService.processSwap(actionRequest);
                    Collections.swap(expectedArray, j, j + 1);
                    assertEquals(gameState.getArray(), expectedArray);
                }
            }
        }
    }
}
