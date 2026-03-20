package com.example.concurrency.locks.and.synchronizers.explicit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

@Component
@Profile("reentract-lock-test")
public class ReentrantLockDemo implements CommandLineRunner {
    private final ReentrantLock lock = new ReentrantLock();
    private int counter=0;
    public void increment(){
        lock.lock();
        try{
            counter++;
            System.out.println(Thread.currentThread().getName()+" incremented to "+counter);
        }finally {
            lock.unlock(); //Must release
        }
    }

    @Override
    public void run(String... args) throws Exception {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        Runnable task = () -> {
            for (int i=0;i<5;i++){
                demo.increment();
            }
        };
        Thread t1 = new Thread(task,"T1");
        Thread t2 = new Thread(task,"T2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
/* we must put unlock() inside finally bcz if exception occurs and unlock not called -> deadlock.
Under high contention, its often better than synchronized. Under lower contention synchronized is
optimized heavily in modern JVM.
*/