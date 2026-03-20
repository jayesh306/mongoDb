package com.example.concurrency.executors.pool.future;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
@Profile("future-cancel-demo")
public class FutureCancelDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("Task completed");
            } catch (InterruptedException e) {
                System.out.println("Task interrupted");
            }
        });
        Thread.sleep(1000);
        future.cancel(true);
        executor.shutdown();
    }
}
