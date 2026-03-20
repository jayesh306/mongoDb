package com.example.concurrency.completable.future.basic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
/* runAsync -> Runnable, No result
supplyAsync -> Supplier, REturn result
 */
@Component
@Profile("runasync-vs-supplyasync")
public class RunAsyncVsSupplyAsyncDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() ->
                System.out.println("runAsync task"));
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            return 100;
                }
                );
        System.out.println(supplyAsync.join());
    }
}
