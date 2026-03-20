package com.example.concurrency.completable.future.basic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
/* Before java 8 we had Future and ExecutorService. But the problem with Future was :
Future<Integer> future = executor.submit(task);
Integer result = future.get(); //BLOCKING
CompletableFuture solves this by async execution, Non blocking pipeline, task chaining, task
combination, error handling
its like -> Future + Promise + Functional Programming

 */
@Component
@Profile("completable-future")
public class CompletableFutureBasicDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Running async task in "+Thread.currentThread().getName());
        });
        future.join();
    }
}
