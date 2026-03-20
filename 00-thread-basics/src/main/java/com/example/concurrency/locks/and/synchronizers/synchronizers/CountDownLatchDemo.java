package com.example.concurrency.locks.and.synchronizers.synchronizers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/* One or more threads wait unitl a set of operations complete.
Latch = One-time gate. Once openend, it stays open.
Uses:
1. Wait for multiple microservices to initialize.
2. Wait for multiple parallel tasks to complete before proceeding.
3. Integration testing(wait for async tasks)
 */

@Component
@Profile("countdown-latchdemo")
public class CountDownLatchDemo implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        int workerCount = 3;
        CountDownLatch latch = new CountDownLatch(workerCount);
        Runnable worker = () -> {
            System.out.println(Thread.currentThread().getName() + " started work");
            sleep(2000);
            System.out.println(Thread.currentThread().getName() + " finished work.");
            latch.countDown();
        };
        for (int i = 0; i < workerCount; i++) {
            new Thread(worker, "Worker-" + i).start();
        }
        System.out.println("Main thread waiting");
        latch.await();
        System.out.println("All workers finished. Main proceeding....");
    }

        private void sleep(long ms){
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
}

