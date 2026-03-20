package com.example.concurrency.locks.and.synchronizers.intrinsic;

public class SynchronizedMethodCounter {
    private int count = 0;
    public synchronized void increment(){
        count++;
    }
    public synchronized int getCount(){
        return count;
    }
}
