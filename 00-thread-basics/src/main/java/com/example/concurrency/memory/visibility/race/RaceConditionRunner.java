package com.example.concurrency.memory.visibility.race;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("race-condition-test")
public class RaceConditionRunner implements CommandLineRunner {
    @Override
    public void run(String[] args) throws Exception {
        Counter c = new Counter();
        Runnable task = ()-> {
            for(int i=0;i<100000;i++){
                c.increment();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Final count: "+c.getCounter());
    }
}
