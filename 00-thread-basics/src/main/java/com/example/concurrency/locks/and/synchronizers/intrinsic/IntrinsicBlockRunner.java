package com.example.concurrency.locks.and.synchronizers.intrinsic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("synchronized-block-runner")
public class IntrinsicBlockRunner implements CommandLineRunner {
    SynchronizedBlockCounter counter = new SynchronizedBlockCounter();
    @Override
    public void run(String... args) throws Exception {
        Runnable task = () -> {
            System.out.println("Entered SynchronizedBlockRunner");
            for(int i=0;i<10000;i++){
                counter.increment();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Synchronized block count : "+counter.getCount());
    }
}
