package com.example.concurrency.locks.and.synchronizers.intrinsic;

public class SynchronizedBlockCounter {
    private int count = 0;
    private final Object lock = new Object();

    public void increment(){
        synchronized (lock){
            count++;
        }
    }
    public int getCount() {
        return count;
    }
}
