package com.example.concurrency.executors.pool.threadpoolexecutor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Profile("rejection-policy-demo")
public class RejectionPolicyDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,2
                ,0, TimeUnit.SECONDS,new ArrayBlockingQueue<>(2)
                ,new ThreadPoolExecutor.CallerRunsPolicy());
        for(int i=1;i<=10;i++){
            int taskId = i;
            executor.execute(() -> {
                System.out.println("Running task "+taskId+" on "+Thread.currentThread().getName());
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){}
            });
            executor.shutdown();
        }
    }
}
