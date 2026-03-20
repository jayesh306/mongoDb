package com.example.concurrency.fork.join.basics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.ForkJoinPool;

@Component
@Profile("fork-join-runner")
public class ForkJoinRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("ForkJoin Demo started!!");
        int[] numbers = new int[10000];
        for(int i=0;i<numbers.length;i++){
            numbers[i]=i;
        }
        ForkJoinPool pool = ForkJoinConfig.createPool();
        ParallelSumTask sumTask = new ParallelSumTask(numbers,0,numbers.length);
        long result = pool.invoke(sumTask);
        System.out.println("Parallel sum result : "+result);
        ForkJoinWorkStealingDemo demo = new ForkJoinWorkStealingDemo(64);
        pool.invoke(demo);
        System.out.println("----------Fork Join Demo Completed.------------");
    }
}
