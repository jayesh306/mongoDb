package com.example.concurrency.locks.and.synchronizers.explicit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Component
@Profile("condition-test")
public class ConditionDemo implements CommandLineRunner {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean ready = false;

    public void awaitCondition(){
        lock.lock();
        try{
            while(!ready){
                condition.await();
            }
            System.out.println("Condition met!");
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }finally {
            lock.unlock();
        }
    }

    public void signalCondition(){
        lock.lock();
        try{
            ready = true;
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
    @Override
    public void run(String... args) throws Exception {
        ConditionDemo demo = new ConditionDemo();
        Thread t1 = new Thread(demo::awaitCondition);
        t1.start();
        Thread.sleep(2000);
        Thread t2 = new Thread(demo::signalCondition);
        t2.start();
    }
}
