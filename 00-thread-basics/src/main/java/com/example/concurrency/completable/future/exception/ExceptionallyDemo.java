package com.example.concurrency.completable.future.exception;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/* CompletableFuture supports async error handling.
 */
@Component
@Profile("exceptional-demo")
public class ExceptionallyDemo implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() ->{
            int x = 10/0;
            return x;
        }).exceptionally(ex -> {
            System.out.println("Error occurred: "+ex);
            return -1;
        });
        System.out.println("Result : "+future.join());
    }
}
