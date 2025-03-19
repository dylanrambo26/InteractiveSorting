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


    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    /*@GetMapping("/")
    public String getGameInfo() {
        return "Game endpoint is working!";
    }

    //Starting a New Game
    /*@PostMapping("/start")
    public GameState startNewGame(@RequestParam String algorithm){
        return gameService.startGame(algorithm);
    }*/

    @CrossOrigin(origins = "http://localhost:63343") // Allow CORS from frontend's origin, testing in WebStorm, host on node.js later
    @PostMapping("/start")
    public GameState startNewGame(@RequestBody StartRequest request){
        return gameService.startGame(request);

    }

    // Processing a User Swap of Elements
    @CrossOrigin(origins = "http://localhost:63343") // Allow CORS from frontend's origin, testing in WebStorm
    @PostMapping("/action")
    public GameState processAction(@RequestBody ActionRequest request) {
        return gameService.processAction(request);
    }


    // Check Game Status
    @GetMapping("/status/{gameId}")
    public GameState getGameStatus(@PathVariable int gameId) {
        return gameService.getGameState(gameId);
    }
}
