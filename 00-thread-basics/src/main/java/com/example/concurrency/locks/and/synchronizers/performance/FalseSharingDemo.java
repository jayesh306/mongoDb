package com.example.concurrency.locks.and.synchronizers.performance;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
/* False sharing happends when two threads modify different variables, but those variables sit
on same CPU cache line. Different threads update them, CPU invalid cache repeatedly.
So it causes performance degradation.
(Rarely asked)
 */
@Component
@Profile("false-sharing-test")
public class FalseSharingDemo implements CommandLineRunner {
    static class Counter{
        volatile long x = 0;
        volatile long y =0;
    }
    private final Counter counter = new Counter();
    @Override
    public void run(String... args) throws Exception {
        Thread t1 = new Thread(() -> {
            for(long i=0;i<100000;i++){
                counter.x++;
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i=0;i<100000;i++) {
                counter.y++;
            }
        });
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println("Time : "+(end-start));
    }
}
