package com.example.concurrency.locks.and.synchronizers.performance;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/* When multiple threads compete for the same lock frequently.
Symptoms : Throughput drops, CPU usage increases, Threads blocked/waiting, Increased latency
Real prod examples : Synchronized logging, Global cache update, Shared statistics counter,
Blocking DB connection pool.
Q. How to reduce lock contentions:
A-> Finer-grained locks, ReadWriteLock, Lock striping, LongAdder, Reduce critical section size.

 */
@Component
@Profile("lock-contention")
public class LockContentionDemo implements CommandLineRunner {

    private final Object lock = new Object();
    private int counter=0;
    @Override
    public void run(String... args) throws Exception {
        Runnable task = () -> {
            for(int i=0;i<1000000;i++){
            synchronized (this) {
                counter++;
            }
            }
        };
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(task,"Thread-1");
        Thread t2= new Thread(task,"Thread-2");
        Thread t3 = new Thread(task,"Thread-3");

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        long end = System.currentTimeMillis();
        System.out.println("Counter : "+counter);
        System.out.println("Time taken : "+(end-start)+" ms");
    }
}
