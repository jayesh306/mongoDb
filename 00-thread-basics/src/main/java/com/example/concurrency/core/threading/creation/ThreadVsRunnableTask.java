package com.example.concurrency.core.threading.creation;

public class ThreadVsRunnableTask extends Thread{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+" | Created by extending Thread");
    }
}
