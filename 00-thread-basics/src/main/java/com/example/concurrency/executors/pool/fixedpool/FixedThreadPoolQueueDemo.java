package com.example.concurrency.executors.pool.fixedpool;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* 2 threads active, 4 tasks waiting in queue.
 */
@Component
@Profile("fixed-thread-pool-queue")
public class FixedThreadPoolQueueDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for(int i=0;i<=6;i++){
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task : "+taskId+ " is starting by "+Thread.currentThread().getName());
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("Finished task : "+taskId);
            });
        }
        executor.shutdown();
    }
}
