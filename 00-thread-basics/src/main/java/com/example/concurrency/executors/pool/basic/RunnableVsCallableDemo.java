package com.example.concurrency.executors.pool.basic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/* Executor Framework is a high-level API for : managing threads, executing tasks asynchronously,
controlling thread pools.
Main interface : Executor, ExecutorService, ScheduledExecutorService
Application Task -> ExecutorService.submit() -> BlockingQueue(task queue) -> Worker Threads
-> Execute Task.
Executor Service internally uses BlockingQueue.
Runnable -> No return value.
Callable -> Return result, throws checked exception.

 */
@Component
@Profile("runnable-vs-callable")
public class RunnableVsCallableDemo implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Runnable task = () -> {
            System.out.println("Runnable executed");
        };
        Callable<Integer> callableTask = () -> {
                return 100;
        };
        executor.submit(task);
        Future<Integer> result = executor.submit(callableTask);
        System.out.println("Callable result : "+result.get());
        executor.shutdown();
    }
}
/* ExecutorService provides thread pool mgmt. Result handling, Shutdown control.
 */
