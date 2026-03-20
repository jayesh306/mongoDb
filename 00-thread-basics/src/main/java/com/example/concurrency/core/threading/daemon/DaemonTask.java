package com.example.concurrency.core.threading.daemon;

public class DaemonTask implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println(Thread.currentThread().getName()+" | Running background task");
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
