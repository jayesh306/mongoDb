package com.example.concurrency.locks.and.synchronizers.explicit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
import java.util.concurrent.locks.ReentrantLock;

@Component
@Profile("lock-interruptible-test")
public class LockInterruptibllyDemo implements CommandLineRunner {
    private final ReentrantLock lock = new ReentrantLock();
    public void execute(){
        try{
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + " acquired lock");
                Thread.sleep(1000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }finally {
                lock.unlock();
            }
        }catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName()+" was interrupted while waiting");
        }
    }

    @Override
    public void run(String... args) throws Exception {
        LockInterruptibllyDemo demo = new LockInterruptibllyDemo();
        Runnable task = () -> demo.execute();
        new Thread(task,"T1").start();
        Thread.sleep(100);
        Thread t2 = new Thread(demo::execute,"Thread-2");
        t2.start();
        Thread.sleep(500);
        t2.interrupt();
    }
}
