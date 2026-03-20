package com.example.concurrency.locks.and.synchronizers.advanced;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.StampedLock;

@Component
@Profile("stamped-lock")
public class StampedLockDemo implements CommandLineRunner {
    private final StampedLock lock = new StampedLock();
    private int balance=100;

    @Override
    public void run(String... args) throws Exception {
        Thread reader = new Thread(() -> {
            long stamp = lock.tryOptimisticRead();
            int currentBalance = balance;
            if(!lock.validate(stamp)){
                stamp= lock.readLock();
                try{
                    currentBalance=balance;
                }finally {
                    lock.unlockRead(stamp);
                }
            }
            System.out.println("Read balance : "+currentBalance);
        });

        Thread writer = new Thread(() -> {
            long stamp = lock.writeLock();
            try{
                balance += 50;
                System.out.println("Updated balance");
            }finally {
                lock.unlockWrite(stamp);
            }
        });
        writer.start();
        reader.start();

    }
}
/* It shows optimistic read, pessimistic read, write lock, lock conversion.
StampedLock has better performance under high-read scenarios. It avoids blocking if no write
is happening.

 */