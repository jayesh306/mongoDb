package com.example.concurrency.memory.visibility;

public class FlagHolder {
    private boolean running = true;
    public void stop(){
        running=false;
    }
    public boolean isRunning(){
        return running;
    }
}
