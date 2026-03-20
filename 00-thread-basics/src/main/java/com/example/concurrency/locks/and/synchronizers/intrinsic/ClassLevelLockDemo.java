package com.example.concurrency.locks.and.synchronizers.intrinsic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("class-level-lock")
public class ClassLevelLockDemo implements CommandLineRunner {
    private static int counter=0;

    public static synchronized void increment(){
        for(int i=0;i<5;i++) {
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented to " + counter);
        }
    }
    @Override
    public void run(String... args) throws Exception {
        Runnable task = ClassLevelLockDemo::increment;
        Runnable task2 = ClassLevelLockDemo::increment;
        Thread t1 = new Thread(task,"thread1");
        Thread t2 = new Thread(task2,"thread2");
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
