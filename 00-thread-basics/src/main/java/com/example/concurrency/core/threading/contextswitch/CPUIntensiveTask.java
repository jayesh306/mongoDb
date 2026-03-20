package com.example.concurrency.core.threading.contextswitch;

public class CPUIntensiveTask implements Runnable{
    @Override
    public void run() {
        for (int i=0;i<5;i++){
            System.out.println(Thread.currentThread().getName()+" | Executing step "+i);
        }
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
