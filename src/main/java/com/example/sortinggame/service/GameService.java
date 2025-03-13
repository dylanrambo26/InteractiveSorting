package com.example.sortinggame.service;
import com.example.sortinggame.model.GameState;
import com.example.sortinggame.model.SwapRequest;
import com.example.sortinggame.service.SortingValidator;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class GameService {
    private final Map<Integer, GameState> games = new HashMap<>();
    private int gameIdCounter = 1;
    public GameState startGame(String algorithm){
        List<Integer> array = generateShuffledArray(5);//TODO Make it so that user can choose size
        GameState gameState = new GameState(gameIdCounter, algorithm, array);
        games.put(gameIdCounter, gameState);
        return games.get(gameIdCounter++);
    }

    public GameState processSwap(SwapRequest swapRequest){
        GameState gameState = games.get(swapRequest.getGameID());
        if (gameState == null){
            return null;
        }

        boolean isValidSwap = SortingValidator.isValidSwap(gameState,swapRequest.getPass(), swapRequest.getIndex1(), swapRequest.getIndex2());

        if(isValidSwap){
            List<Integer> array = gameState.getArray();
            Collections.swap(array, swapRequest.getIndex1(), swapRequest.getIndex2());
            gameState.incrementSwaps();
        }

        gameState.setSorted(isSorted(gameState.getArray()));
        return gameState;
    }

    private List<Integer> generateShuffledArray(int size){
        List<Integer> shuffledArray = new ArrayList<>();
        for(int i = 1; i <= size; i++){
            shuffledArray.add(i);
        }
        Collections.shuffle(shuffledArray);
        return shuffledArray;
    }

    private boolean isSorted(List<Integer> arr){
        for(int i = 0; i < arr.size() - 1; i++){
            if(arr.get(i) > arr.get(i+1)){
                return false;
            }
        }
        return true;
    }

    public GameState getGameState(int gameId){
        return games.get(gameId);
    }

    /*private void printList(List<Integer> list){
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i) + ",");
        }
    }*/

    /*public static void main(String[] args){
        int k = 5;
        GameService obj = new GameService();

        obj.printList(obj.generateRandomArray(k));
    }*/

}
