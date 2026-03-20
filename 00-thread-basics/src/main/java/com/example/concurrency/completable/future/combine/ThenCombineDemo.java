package com.example.concurrency.completable.future.combine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/* Used for parallel tasks.
Example : Fetch User, Fetch Orders, Combine results
 */
@Component
@Profile("then-combine")
public class ThenCombineDemo implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() ->"Jayesh");
        CompletableFuture<Integer> orderFuture = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<String> result = userFuture.thenCombine(orderFuture,(user,order) ->
                user+" has "+order+" orders");
        System.out.println(result.join());

    }
}
