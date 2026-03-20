//package com.example.concurrency.completable.future.advanced;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.CompletableFuture;
//
//@Component
//@Profile("async-pipeline-demo")
//public class AsyncPipelineDem implements CommandLineRunner {
//    @Override
//    public void run(String... args) throws Exception {
//        CompletableFuture<String> pipeline = CompletableFuture.supplyAsync(() ->
//                fetchUser()).thenApply(user -> fetchOrder(user)).thenApply(order -> processPayment(order));
//        System.out.println(pipeline.join());
//        }
//    }
//    static String fetchUser(){
//        return "User123";
//    }
//    static String fetchOrder(String user){
//        return "Orders for "+user;
//    }
//    static String processPayment(String order){
//        return "Payment processed for "+order;
//    }
//
//
//}
