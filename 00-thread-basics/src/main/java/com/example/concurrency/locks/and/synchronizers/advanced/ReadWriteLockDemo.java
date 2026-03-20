package com.example.concurrency.locks.and.synchronizers.advanced;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
@Profile("read-write-lock")
public class ReadWriteLockDemo implements CommandLineRunner {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private int value = 0;

    @Override
    public void run(String... args) throws Exception {
        Runnable reader = () -> {
            lock.readLock().lock();
            try{
                System.out.println(Thread.currentThread().getName()+" reading value : "+value);
                Thread.sleep(2000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }finally {
                lock.readLock().unlock();
            }
        };

        Runnable writer = () -> {
            lock.writeLock().lock();
            try{
                System.out.println(Thread.currentThread().getName()+" will be incrementing the value "+value+" to 1.");
                value++;
                Thread.sleep(2000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }finally {
                lock.writeLock().unlock();
            }
        };
        new Thread(reader,"Reader-1").start();
        new Thread(writer,"Writer-1").start();
        new Thread(reader,"Reader-2").start();

    }
}

/* In ReentrantReadWriteLock , multiple readers are allowed and one writer is allowed.
Writer blocks readers readers blocks writer(depending on fairness)
It can be used in real world scenarios where many threads read data but rarely threads update data.
Synchronized vs ReentrantReadWriteLock
synchronized -> Built-in JVM monitor lock, Acquires exclusive lock, Only one thread can enter
the critical section, Simpler but less flexible, No timeout support, No fairness configuration,
Lock automatically released.
ReentrantReadWriteLock -> Provides two locks : Read lock and write lock, multiple readers allowed
simultaneously, only one writer allowed.
 */