package com.example.concurrency.locks.and.synchronizers.intrinsic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("synchronized-method-runner")
public class IntrinsicLockRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        SynchronizedMethodCounter counter = new SynchronizedMethodCounter();
        Runnable task = () -> {
            System.out.println("Entered SynchronizedMethodCounter");
            for(int i=0;i<100000;i++){
                counter.increment();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Synchronized count = "+counter.getCount());
    }
}
