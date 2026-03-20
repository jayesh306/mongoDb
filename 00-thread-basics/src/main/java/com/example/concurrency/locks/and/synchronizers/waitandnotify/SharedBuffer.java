package com.example.concurrency.locks.and.synchronizers.waitandnotify;

public class SharedBuffer {
    private int value;
    private boolean available = false;

    public synchronized void produce(int val) throws InterruptedException{
        while(available){
            wait();
        }
        value=val;
        available=true;
        notifyAll();
    }
    public synchronized int consume() throws InterruptedException{
        while(!available){
            wait();
        }
        available=false;
        notifyAll();
        return value;
    }
}
