package com.example.sortinggame.controller;

import com.example.sortinggame.model.GameState;
import com.example.sortinggame.model.StartRequest;
import com.example.sortinggame.model.ActionRequest;
import com.example.sortinggame.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game") //Base path for all endpoints
public class GameController {
    //GameService instance for executing game logic
    private final GameService gameService;

    //Constructor initializes gameService
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    //Starts the game, initializing the gameState for the user.
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/start")
    public GameState startNewGame(@RequestBody StartRequest request){
        System.out.println("Reached");
        return gameService.startGame(request);

    }

    // Processing a User Action on the elements on the unsorted array.
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/action")
    public GameState processAction(@RequestBody ActionRequest request) {
        System.out.println("Reached Swap");
        return gameService.processAction(request);
    }


    // Check Game Status
    @GetMapping("/status/{gameId}")
    public GameState getGameStatus(@PathVariable int gameId) {
        return gameService.getGameState(gameId);
    }
}
