package com.example.Java8Lab.completablefuture;

import com.example.Java8Lab.BaseRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

@Component
@Order(12)
public class CompletableFutureRunner extends BaseRunner {
    @Override
    protected void execute() {
        System.out.println("=== 1. supplyAsync ===");
        supplyAsyncDemo();

        System.out.println("\n=== 2. thenApply vs thenCompose ===");
        thenApplyVsCompose();

        System.out.println("\n=== 3. thenCombine ===");
        thenCombineDemo();

        System.out.println("\n=== 4. Exception Handling");
        exceptionHandlingDemo();

        System.out.println("\n=== 5. allOf Demo ===");
        allOfDemo();
    }

    private void supplyAsyncDemo(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "Data fetched";
        });
        future.thenAccept(System.out::println).join();
    }

    private void thenApplyVsCompose(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Jayesh");

        CompletableFuture<Integer> length1 = future.thenApply(String::length);
        CompletableFuture<Integer> length2 = future.thenCompose(name ->
                CompletableFuture.supplyAsync(() -> name.length()));
        System.out.println("thenApply: "+length1.join());
        System.out.println("thenCompose: "+length2.join());
    }

    private void thenCombineDemo(){
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> 20);
        CompletableFuture<Integer> combined = f1.thenCombine(f2,Integer::sum);

        System.out.println("Combined result : "+combined.join());
    }
    private void exceptionHandlingDemo(){
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10/0)
                .exceptionally(ex -> {
            System.out.println("Exception : "+ex.getMessage());
            return -1;
        });
        System.out.println("Handled result: "+future.join());
    }
    private void allOfDemo(){
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "A");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "B");
        CompletableFuture<Void> all = CompletableFuture.allOf(f1,f2);
        all.thenRun(() -> {
            System.out.println("All completed");
        }).join();
    }
    private void sleep(int ms){
        try{
            Thread.sleep(ms);
        }catch (InterruptedException ignored){}
    }
}
