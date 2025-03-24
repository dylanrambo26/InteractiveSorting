package com.example.sortinggame.service;
import com.example.sortinggame.model.GameState;
import com.example.sortinggame.model.StartRequest;
import com.example.sortinggame.model.ActionRequest;
import org.springframework.stereotype.Service;
import java.util.*;

/*Service layer for Spring Boot. Handles start of game, action requests by user, and internal logic */
@Service
public class GameService {
    private final Map<Integer, GameState> games = new HashMap<>(); //Maps a game state to a game id so that each game is unique.
    private int gameIdCounter = 1; //Keeps track of the current gameID value to associate with a game state

    //Starts the game with a shuffled array for the user to sort. Initializes game state with the current gameID.
    public GameState startGame(StartRequest startRequest){
        List<Integer> array = generateShuffledArray(startRequest.getArraySize());
        GameState gameState = new GameState(gameIdCounter, startRequest.getAlgorithm(), array);
        games.put(gameIdCounter, gameState);
        return games.get(gameIdCounter++);
    }

    /*Given an actionRequest with several arguments, process the action to validate the action or not according
    to the selected sorting algorithm.*/
    public GameState processAction(ActionRequest actionRequest){
        GameState gameState = games.get(actionRequest.getGameID());
        if (gameState == null){
            return null;
        }

        boolean isValidAction = SortingValidator.isValidSortParameters(gameState, actionRequest.getPass(), actionRequest.getIndex1(), actionRequest.getIndex2());

        //Performs the action requested if it is valid and performs the action associated with the selected algorithm.
        if(isValidAction){
            String algorithm = gameState.getAlgorithm();

            if(algorithm.equals("bubble_sort") || algorithm.equals("selection_sort")){
                Collections.swap(gameState.getArray(), actionRequest.getIndex1(), actionRequest.getIndex2());
            }
            else if(algorithm.equals("insertion_sort")){
                insertIntoSorted(gameState, actionRequest.getIndex2());
            }
            gameState.incrementSwaps();
        }

        //Check if the array is sorted before returning data to the user.
        boolean isSorted = isSorted(gameState.getArray());
        if(isSorted){
            gameState.setMessage("List is Sorted. :)");
        }
        gameState.setSorted(isSorted);
        return gameState;
    }

    //Creates a shuffled array for the user to sort. Elements consist of integers from 1 - size of array specified by the user
    private List<Integer> generateShuffledArray(int size){
        List<Integer> shuffledArray = new ArrayList<>();
        for(int i = 1; i <= size; i++){
            shuffledArray.add(i);
        }
        Collections.shuffle(shuffledArray);
        return shuffledArray;
    }

    //Insertion procedure for insertion sort
    private void insertIntoSorted(GameState gameState, int i){
        List<Integer> array = gameState.getArray();
        int key = array.get(i);
        int j = i - 1;
        while (j >= 0 && array.get(j) > key) {
            array.set(j + 1, array.get(j));
            j = j - 1;
        }
        array.set(j + 1, key);
    }

    //Checks if the array is in ascending sorted order.
    private boolean isSorted(List<Integer> arr){
        for(int i = 0; i < arr.size() - 1; i++){
            if(arr.get(i) > arr.get(i+1)){
                return false;
            }
        }
        return true;
    }

    //Method for the front-end to receive the current game state.
    public GameState getGameState(int gameId){
        GameState gameState = games.get(gameId);
        System.out.println(gameId + " is the gameID and array is" + gameState.getArray());
        return gameState;
    }
}
