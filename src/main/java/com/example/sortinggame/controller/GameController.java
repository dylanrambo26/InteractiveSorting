package com.example.sortinggame.controller;

import com.example.sortinggame.model.GameState;
import com.example.sortinggame.model.SwapRequest;
import com.example.sortinggame.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game") //Base path for all endpoints
public class GameController {
    //GameService instance for executing game logic
    private final GameService gameService;


    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    //Starting a New Game
    @PostMapping("/start")
    public GameState startNewGame(@RequestParam String algorithm){
        return gameService.startGame(algorithm);
    }

    // Processing a User Swap of Elements
    @PostMapping("/swap")
    public GameState processSwap(@RequestBody SwapRequest request) {
        return gameService.processSwap(request);
    }

    // Check Game Status
    @GetMapping("/status/{gameId}")
    public GameState getGameStatus(@PathVariable int gameId) {
        return gameService.getGameState(gameId);
    }
}
