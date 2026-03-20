package com.example.concurrency.memory.visibility.atomic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("atomic-counter-test")
public class AtomicCounterRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        AtomicCounter counter = new AtomicCounter();
        Runnable task = () -> {
            System.out.println("Entered runnable from AtomicCounterRunner");
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
        System.out.println("Final atomic count: "+counter.getCount());
    }
}
