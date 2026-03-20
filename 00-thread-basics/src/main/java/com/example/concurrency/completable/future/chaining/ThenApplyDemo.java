package com.example.concurrency.completable.future.chaining;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/* Think of it like pipeline processing.
Task1 -> Task2 -> Task3
Ex : Fetch user -> Extract name -> convert uppercase
 */
@Component
@Profile("then-apply")
public class ThenApplyDemo implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "jayesh")
                .thenApply(String::toUpperCase).thenApply(name -> "Hello "+name);
        System.out.println(future.join());

    }
}
