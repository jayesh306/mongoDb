package com.example.concurrency.core.threading.creation;

public class RunnablePreferredTask implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" | Created using Runnable(preferred)");
    }
}
