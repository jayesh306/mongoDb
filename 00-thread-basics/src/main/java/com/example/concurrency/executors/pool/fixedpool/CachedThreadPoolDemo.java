package com.example.concurrency.executors.pool.fixedpool;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* Creates thread dynamically(does not have a fixed size) : 0 -> unlimited threads(depends on task load).
Used for : short-lived async tasks.
It creates threads as needed. Reuses idle threads and it has no queue. Basically if no idle thread
is available, it creates a new one immediately.
 */
@Component
@Profile("cached-thread-pool")
public class CachedThreadPoolDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i=1;i<=10;i++){
            int taskId = i;
            executor.submit(() ->
                    System.out.println("Current task : "+taskId+" started by thread : "+Thread.currentThread().getName()));
        }
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        executor.shutdown();
    }
}
