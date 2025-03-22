package com.example.sortinggame.service;
import com.example.sortinggame.model.GameState;
import com.example.sortinggame.model.StartRequest;
import com.example.sortinggame.model.ActionRequest;
import jakarta.servlet.http.Part;
import org.springframework.boot.autoconfigure.amqp.AbstractRabbitListenerContainerFactoryConfigurer;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class GameService {
    private final Map<Integer, GameState> games = new HashMap<>();
    private int gameIdCounter = 1;

    public static class Partition{
        private final int left;
        private final int right;
        private Partition leftChild;
        private Partition rightChild;
        private Partition parent;


        public Partition(int left, int right){
            this.left = left;
            this.right = right;
        }

        public int getLeft(){
            return left;
        }
        public int getRight(){
            return right;
        }
        public Partition getLeftChild(){
            return leftChild;
        }
        public Partition getRightChild(){
            return rightChild;
        }
        public void setLeftChild(Partition leftChild){
            this.leftChild = leftChild;
            if(leftChild != null){
                leftChild.parent = this;
            }
        }
        public void setRightChild(Partition rightChild){
            this.rightChild = rightChild;
            if(rightChild != null){
                rightChild.parent = this;
            }
        }
    }
    public GameState startGame(StartRequest startRequest){
        List<Integer> array = generateShuffledArray(startRequest.getArraySize());
        GameState gameState = new GameState(gameIdCounter, startRequest.getAlgorithm(), array);

        //Merge sort partitioning before user actions
        if(startRequest.getAlgorithm().equals("merge_sort")){
            List<Integer> mergeArray = gameState.getArray();
            List<Partition> partitionList = listOfPartitions(new ArrayList<>(), 0, mergeArray.size() - 1);
            gameState.setPartitionList(partitionList);
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

    //Returns a shuffled list of elements ready for the user to sort
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

    //Returns the list of partitions of the array
    private List<Partition> listOfPartitions(List<Partition> partitions, int left, int right){
        mergeSortPartitionIndexes(partitions, left, right, null, false);
        return partitions;
    }

    //Tracks the indexes of each partition during the divide step of merge sort. Also tracks the parent and child partitions
    //for display later.
    private void mergeSortPartitionIndexes(List<Partition> partitions, int left, int right, Partition parent, boolean isLeftChild){
        Partition currentPartition = new Partition(left, right);
        partitions.add(currentPartition);

        if(parent != null){
            if(isLeftChild){
                parent.setLeftChild(currentPartition);
            }
            else{
                parent.setRightChild(currentPartition);
            }
        }

        if(left < right){
            int midpoint = Math.floorDiv(left + right, 2);
            mergeSortPartitionIndexes(partitions, left, midpoint, currentPartition, true);
            mergeSortPartitionIndexes(partitions, midpoint + 1, right, currentPartition, false);
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
}
