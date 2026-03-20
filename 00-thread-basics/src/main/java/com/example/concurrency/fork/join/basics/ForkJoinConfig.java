package com.example.concurrency.fork.join.basics;

import java.util.concurrent.ForkJoinPool;
/* Creates and config ForkJoinPool. The parallelism level is typically set to the number of
available processors to maximize CPU utilization.
 */
public class ForkJoinConfig {
    public static ForkJoinPool createPool(){
        int parallelism = Runtime.getRuntime().availableProcessors();
        //It means number of worker threads = number of CPU cores, 4 cores = 4 threads
        System.out.println("Parallelism level = "+parallelism);
        return new ForkJoinPool(parallelism);
        //we get a work-stealing thread-pool.
    }
}
