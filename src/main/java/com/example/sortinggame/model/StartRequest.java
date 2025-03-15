package com.example.sortinggame.model;

public class StartRequest {
    private String algorithm;
    private int arraySize;

    public StartRequest(){

    }

    public StartRequest(String algorithm, int arraySize){
        this.algorithm = algorithm;
        this.arraySize = arraySize;
    }

    public String getAlgorithm(){
        return algorithm;
    }

    public int getArraySize(){
        return arraySize;
    }

    public void setAlgorithm(String algorithm){
        this.algorithm = algorithm;
    }
    public void setArraySize(int arraySize){
        this.arraySize = arraySize;
    }

}
