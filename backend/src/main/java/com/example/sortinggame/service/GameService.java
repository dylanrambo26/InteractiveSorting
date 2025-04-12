package com.example.sortinggame.service;
import com.example.sortinggame.model.GameState;
import com.example.sortinggame.model.StartRequest;
import com.example.sortinggame.model.ActionRequest;
import com.example.sortinggame.repository.GameStateRepository;
import org.springframework.stereotype.Service;
import java.util.*;

/*Service layer for Spring Boot. Handles start of game, action requests by user, and internal logic */
@Service
public class GameService {

    private final GameStateRepository repository;
    public GameService(GameStateRepository repository){
        this.repository = repository;
    }

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

    //Starts the game with a shuffled array for the user to sort. Initializes game state with the current gameID.
    public GameState startGame(StartRequest startRequest){
        List<Integer> array = generateShuffledArray(startRequest.getArraySize());
        GameState gameState = new GameState(startRequest.getAlgorithm(), array);


        //Merge sort partitioning before user actions
        if(startRequest.getAlgorithm().equals("merge_sort")){
            List<Integer> mergeArray = gameState.getArray();
            List<Partition> partitionList = listOfPartitions(new ArrayList<>(), 0, mergeArray.size() - 1);
            gameState.setPartitionList(partitionList);
        }

        return repository.save(gameState);
    }

    /*Given an actionRequest with several arguments, process the action to validate the action or not according
    to the selected sorting algorithm.*/
    public GameState processAction(ActionRequest actionRequest){
        //TODO Add merge sort validation
        Optional<GameState> optional = repository.findById(actionRequest.getGameID());
        if (optional.isEmpty()){
            return null;
        }
        GameState gameState = optional.get();
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
        return repository.save(gameState); //update gameState in DB
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
        return repository.findById(gameId).orElse(null);
    }
}
