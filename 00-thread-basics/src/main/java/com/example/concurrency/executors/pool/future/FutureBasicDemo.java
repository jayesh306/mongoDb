package com.example.concurrency.executors.pool.future;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* Future represents the result of a computation that will be available in the future.
Basically, its used when you submit a task to a thread pool and want to retrieve the result later.
Task submitted -> executed in another thread -> Future object -> holds the result.
Without Future, we cannot easilt track or retrieve results from async tasks. It allows you to:
- Get result from async task.
- Check if task is completed.
- Cancel a running task.
- Handle async execution safely.
 */
@Component
@Profile("future-basic-test")
public class FutureBasicDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            Thread.sleep(2000);
            return 50;
        });
        System.out.println("Waiting for results");
        Integer result = future.get();
        System.out.println("Result: "+result);
        executor.shutdown();
    }
}
