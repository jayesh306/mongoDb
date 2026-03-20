package com.example.concurrency.executors.pool.threadpoolexecutor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* If running threads < corePoolSize -> create new thread
Else -> put task into queue
If queue full -> create new thread (up to maxPoolSize)
If maxPoolSize reached -> reject task

 */
@Component
@Profile("custom-thread-pool-executor")
public class CustomThreadPoolExecutorDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,4,60, TimeUnit.SECONDS,new ArrayBlockingQueue<>(2));
        for(int i=1;i<=10;i++){
            int taskId = i;
            executor.execute(() -> {
                System.out.println("task : "+taskId+" executed by "+Thread.currentThread().getName());
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}
