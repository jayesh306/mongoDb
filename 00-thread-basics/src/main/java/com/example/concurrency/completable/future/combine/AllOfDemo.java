package com.example.concurrency.completable.future.combine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/* Used when multiple futures must complete.
Ex : Call Service A
Call Service B
Call Service C
Wait for all
//basically it waits for multiple futures to complete.
 */
@Component
@Profile("all-of-demo")
public class AllOfDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() ->
                System.out.println("Service A"));
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() ->
                System.out.println("Service B"));
        CompletableFuture<Void> f3 = CompletableFuture.runAsync(() ->
                System.out.println("Service C"));
        CompletableFuture.allOf(f1,f2,f3).join();
        System.out.println("All services completed");
    }
}
