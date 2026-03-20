package com.example.concurrency.memory.visibility.volatilefix;

public class VolatileFlagHolder {
    private volatile boolean running = true;
    public void stop(){
        running=false;
    }
    public boolean isRunning(){
        return running;
    }
}
