package com.example.concurrency.completable.future.exception;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/* Handles both success and failure.
 */
@Component
@Profile("handle-demo")
public class HandleDemo implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10/0)
                .handle((result, ex) -> {
                    if(ex != null){
                        return -1;
                    }
                    return result;
                });
        System.out.println(future.join());
    }
}
