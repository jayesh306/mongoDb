package com.example.concurrency.executors.pool.fixedpool;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* Fixed number of threads. Tasks wait in queue.
Only 3 threads run at once. Remaining tasks goes to BlockingQueue.
It can create multiple worker threads and unlimited task queue. Architecture :
Tasks -> LinkedBlockingQueue -> 3 Threads
 */
@Component
@Profile("fixed-thread-pool")
public class FixedThreadPoolDemo implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i=0;i<=10;i++){
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task : "+taskId+" executed by "+Thread.currentThread().getName());
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
                System.out.println("Finished task : "+taskId);
            });
        }
        executor.shutdown();
    }
}
