package com.example.concurrency.locks.and.synchronizers.explicit;

import jakarta.annotation.security.RunAs;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Component
@Profile("try-lock-test")
public class TryLockTimeoutDemo implements CommandLineRunner {
    private final ReentrantLock lock = new ReentrantLock();

    public void process(){
        try{
            if(lock.tryLock(2, TimeUnit.SECONDS)){
                try{
                    System.out.println(Thread.currentThread().getName()+" acquired lock");
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }finally {
                    lock.unlock();
                }
            }else{
                System.out.println(Thread.currentThread().getName()+" could not acquire lock");
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        TryLockTimeoutDemo demo = new TryLockTimeoutDemo();
        Runnable task2 = () -> demo.process();
        new Thread(demo::process,"T1").start();
        new Thread(task2,"T2").start();
    }
}
/* we use tryLock to avoid deadlock, implement backoff strategies and resource-limited systems.

 */