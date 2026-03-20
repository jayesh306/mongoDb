package com.example.concurrency.locks.and.synchronizers.advanced;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Component
@Profile("deadlock-prevention-tryLock")
public class DeadLockPreventionDemo implements CommandLineRunner {
    private final ReentrantLock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();
    @Override
    public void run(String... args) throws Exception {
        Thread t1 = new Thread(() -> acquireLocks(lock1,lock2));
        Thread t2 = new Thread(() -> acquireLocks(lock2,lock1));
        t2.start();
        t1.start();
    }
    public void acquireLocks(ReentrantLock firstLock, ReentrantLock secondLock){
        while(true){
            boolean gotFirst = false;
            boolean gotSecond = false;
            try{
                gotFirst = firstLock.tryLock(1, TimeUnit.SECONDS);
                gotSecond = secondLock.tryLock(1,TimeUnit.SECONDS);
                if(gotFirst && gotSecond){
                    System.out.println(Thread.currentThread().getName()+" acquired both locks.");
                    break;
                }
                Thread.sleep(500);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }finally {
                if(gotFirst && !gotSecond){
                    firstLock.unlock();
                }
                if(!gotFirst && gotSecond){
                    secondLock.unlock();
                }
            }
        }
    }
}

/* Showed prevention using tryLock(), Timeout and Lock Ordering.

 */