package com.example.sortinggame.service;
import com.example.sortinggame.model.GameState;
import com.example.sortinggame.model.StartRequest;
import com.example.sortinggame.model.ActionRequest;
import org.springframework.boot.autoconfigure.amqp.AbstractRabbitListenerContainerFactoryConfigurer;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class GameService {
    private final Map<Integer, GameState> games = new HashMap<>();
    private int gameIdCounter = 1;
    public GameState startGame(StartRequest startRequest){
        List<Integer> array = generateShuffledArray(startRequest.getArraySize());
        GameState gameState = new GameState(gameIdCounter, startRequest.getAlgorithm(), array);
        //Merge sort partitioning before user actions
        if(startRequest.getAlgorithm().equals("merge_sort")){
            List<Integer> mergeArray = gameState.getArray();
            mergeSortPartitions(mergeArray, gameState.getMergeSortSubarrays(), mergeArray.get(0), mergeArray.get(mergeArray.size() - 1), 1);
        }
        games.put(gameIdCounter, gameState);
        return games.get(gameIdCounter++);
    }

    public GameState processAction(ActionRequest actionRequest){
        GameState gameState = games.get(actionRequest.getGameID());
        if (gameState == null){
            return null;
        }

        boolean isValidAction = SortingValidator.isValidSortParameters(gameState, actionRequest.getPass(), actionRequest.getIndex1(), actionRequest.getIndex2());

        if(isValidAction){
            String algorithm = gameState.getAlgorithm();

            System.out.println("Before action: " + gameState.getArray());
            if(algorithm.equals("bubble_sort") || algorithm.equals("selection_sort")){
                Collections.swap(gameState.getArray(), actionRequest.getIndex1(), actionRequest.getIndex2());
            }
            else if(algorithm.equals("insertion_sort")){
                insertIntoSorted(gameState, actionRequest.getIndex2());
            }
            System.out.println("After action: " + gameState.getArray());
            gameState.incrementSwaps();
        }
        else{
            System.out.println("Not a Valid Swap");
        }
        boolean isSorted = isSorted(gameState.getArray());
        if(isSorted){
            gameState.setMessage("List is Sorted. :)");
        }
        gameState.setSorted(isSorted);
        //return new GameState(gameState.getGameId(), gameState.getAlgorithm(), gameState.getArray());
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

    private void mergeSortPartitions(List<Integer> array, List<List<Integer>> subarrays, int left, int right, int divisionStep){
        int numDivisions = divisionStep * 2;
        if(left < right){
            int midpoint = Math.floorDiv(left + right, 2);
            List<Integer> leftArray = new ArrayList<>(array.subList(left,midpoint));
            List<Integer> rightArray = new ArrayList<>(array.subList(midpoint + 1, right));
            for(int i = 1; i < numDivisions; i++){
                subarrays.set(i, rightArray);
                subarrays.set(i - 1, leftArray);
            }
        }
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
        GameState gameState = games.get(gameId);
        System.out.println(gameId + " is the gameID and array is" + gameState.getArray());
        return gameState;
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
