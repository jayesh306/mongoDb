package com.example.concurrency.locks.and.synchronizers.performance;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* CAS = Compare and Swap. Used internally by : AtomicInteger, ConcurrentHashMap, LongAdder
It avoids context switching, kernel blocking and provides better scalability under moderate
contention.
In real production : Atomic counters, Metrics, Rate limiters, Lock-free data structure
Spring Boot Actuator metrics internally rely on CAS-styles structures.
 */
@Component
@Profile("cas-vs-lock")
public class CASvsLockDemo implements CommandLineRunner {
    private int lockCounter = 0;
    private final Object lock = new Object();
    private final AtomicInteger casCounter = new AtomicInteger(0);

    @Override
    public void run(String... args) throws Exception {
        Runnable lockTest = () -> {
            System.out.println("Doing lockTest");
            for(int i=0;i<100000;i++) {
                synchronized (lock) {
                    lockCounter++;
                }
            }
        };
        Runnable casTask = () -> {
            System.out.println("Doing CasTest");
            for(int i=0;i<100000;i++) {
                synchronized (lock) {
                    casCounter.incrementAndGet();
                }
            }
        };
        long startLock = System.currentTimeMillis();
        runThreads(lockTest);
        long endLock = System.currentTimeMillis();

        long startCas = System.currentTimeMillis();
        runThreads(casTask);
        long endCas = System.currentTimeMillis();

        System.out.println("Lock time: "+(endLock-startLock));
        System.out.println("CAS time: "+(endCas-startCas));

    }
    private void runThreads(Runnable task) throws InterruptedException{
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
