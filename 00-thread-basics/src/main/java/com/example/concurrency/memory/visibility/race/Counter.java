package com.example.concurrency.memory.visibility.race;

public class Counter {
    private int count=0;
    public void increment(){
        count++;
    }
    public int getCounter(){
        return count;
    }
}
