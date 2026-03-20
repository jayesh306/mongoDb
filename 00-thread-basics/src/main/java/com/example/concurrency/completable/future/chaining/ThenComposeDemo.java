package com.example.concurrency.completable.future.chaining;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/* Used when second async task depends on first result
Ex : Get userId -> Fetch user details
 */
@Component
@Profile("then-compose-demo")
public class ThenComposeDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "user123")
                .thenCompose(id -> CompletableFuture.supplyAsync(() ->
                        "Details for "+id));
        System.out.println(future.join());
    }
}
