package com.example.concurrency.locks.and.synchronizers.explicit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

@Component
@Profile("fair-vs-unfair-locks")
public class FairVsUnfairLockDemo implements CommandLineRunner {

    private final ReentrantLock fairLock = new ReentrantLock(true);
    private final ReentrantLock unfairLock = new ReentrantLock(false);

    public void testLock(ReentrantLock lock){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" acquired lock");
            Thread.sleep(2000);
        }catch (InterruptedException e){
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        FairVsUnfairLockDemo demo = new FairVsUnfairLockDemo();
        Runnable task = () ->{
            demo.testLock(unfairLock);
        };
        for(int i=0;i<5;i++){
            new Thread(task,"Thread-"+i).start();
        }
    }
}
/* Fairness means FIFO ordering. And we shouldn't always use fair lock.
Fair lock : lower throughput, more context switching. Unfair lock : Better performance, risk of starvation
Prod systems -> mostly unfair locks

 */