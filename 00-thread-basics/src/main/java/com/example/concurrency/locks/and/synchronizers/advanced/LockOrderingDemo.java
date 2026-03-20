package com.example.concurrency.locks.and.synchronizers.advanced;
//Best Practice in real systems

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("lock-ordering-demo")
public class LockOrderingDemo implements CommandLineRunner {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    @Override
    public void run(String... args) throws Exception {
        Thread t1 = new Thread(() -> performTask(lock1,lock2));
        Thread t2 = new Thread(() -> performTask(lock1,lock2));
        t1.start();
        t2.start();
    }

    public void performTask(Object lock1, Object lock2){
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName()+" got access for lock1");
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName()+" got access for lock2");
            }
        }
    }
}
